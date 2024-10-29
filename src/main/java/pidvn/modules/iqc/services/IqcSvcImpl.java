package pidvn.modules.iqc.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.IqcLevelOfControl;
import pidvn.entities.one.IqcRequest;
import pidvn.entities.one.IqcResults;
import pidvn.mappers.one.iqc.IqcMapper;
import pidvn.modules.iqc.models.*;
import pidvn.repositories.one.IqcLevelOfControlRepo;
import pidvn.repositories.one.IqcRequestRepo;
import pidvn.repositories.one.IqcResultsRepo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class IqcSvcImpl implements IqcSvc {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IqcMapper iqcMapper;

    @Autowired
    private IqcRequestRepo iqcRequestRepo;

    @Autowired
    private IqcResultsRepo iqcResultsRepo;

    @Autowired
    private IqcLevelOfControlRepo iqcLevelOfControlRepo;

    @Override
    public List<IqcRequestDto> getIqcRequests(SearchDto searchDto) {
        return this.iqcMapper.getIqcRequests(searchDto);
    }

    @Override
    public IqcRequestDto getIqcRequest(String requestNo) {
        return this.iqcMapper.getIqcRequest(requestNo);
    }

    @Override
    public List<PurWhRecordDto> getSlipNo() {
        return this.iqcMapper.getSlipNo();
    }

    @Override
    public Map<Object, Object> createIqcRequest(IqcRequestDto iqcRequestDto) {
        Map<Object, Object> result = new HashMap<Object, Object>();
        if (iqcRequestDto.getType().equals("N")) {
            // TODO: tạo request IQC hàng OUTSIDE
            return this.createIqcRequestOutSide(iqcRequestDto);
        } else if (iqcRequestDto.getType().equals("R")) {
            // TODO: tạo request hàng 6 tháng
            return this.createIqcRequestRecheck(iqcRequestDto);
        } else if (iqcRequestDto.getType().equals("S")) {
            // TODO: tạo request hàng sorting
        }
        return result;
    }

    @Override
    public IqcRequestDto updateIqcRequest(IqcRequestDto iqcRequestDto) {
        IqcRequest request = this.modelMapper.map(iqcRequestDto, IqcRequest.class);
        return this.modelMapper.map(this.iqcRequestRepo.save(request), IqcRequestDto.class);
    }

    @Override
    public List<IqcResultDto> getIqcResults(String requestNo) {
        return this.iqcMapper.getIqcResults(requestNo);
    }

    @Override
    public List<IqcLevelOfControl> getIqcLevelOfControls() {
        return this.iqcLevelOfControlRepo.findAll();
    }

    @Override
    public List<IqcResultDto> evaluateLotNos(List<IqcResultDto> iqcResults) {
        List<IqcResults> data = iqcResults.stream().map(item -> modelMapper.map(item, IqcResults.class)).collect(Collectors.toList());
        List<IqcResults> results = this.iqcResultsRepo.saveAll(data);
        return iqcResults;
    }

    @Override
    public Map<String, Object> getLotsInventory() {

        Map<String, Object> result = new HashMap<>();

        // Masters
        List<PihStoreDto> masters = this.iqcMapper.getLotsInventory("master");

        // Details
        List<PihStoreDto> Details = this.iqcMapper.getLotsInventory("detail");

        result.put("masters", masters);
        result.put("details", Details);

        return result;
    }

    @Override
    public List<PihStoreDto> prepareDataCreateRequest(SearchDto searchDto) {
        return this.iqcMapper.prepareDataCreateRequest(searchDto);
    }



    /**
     * Tạo mã RequestNo
     * @return
     */
    private String generateIqcRequestNo() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String date = formatter.format(new Date());
        int sequence = this.iqcRequestRepo.getTotalRequestInDay() + 1;
        return "IQC-" + date + "-" + String.format("%02d", sequence);
    }

    /**
     * Tạo request Iqc hàng OUTSIDE
     *
     * @param iqcRequestDto
     * @return
     */
    private Map<Object, Object> createIqcRequestOutSide(IqcRequestDto iqcRequestDto) {

        Map<Object, Object> result = new HashMap<Object, Object>();

        /**
         * Lưu thông tin Iqc Request vào bảng iqc_request
         */

        iqcRequestDto.setRequestNo(this.generateIqcRequestNo());
        iqcRequestDto.setClassParam("O");
        IqcRequest iqcRequest = this.iqcRequestRepo.save(this.modelMapper.map(iqcRequestDto, IqcRequest.class));

        /**
         * Lưu thông tin vào bảng iqc_result
         */
        List<PurWhRecordDto> records = this.iqcMapper.getPurWhRecords(iqcRequestDto);

        for (PurWhRecordDto item : records) {
            item.setId(null);
            item.setClassParam("O");
            item.setType("N");
            item.setpDate(item.getDate());
            item.setDate(new Date());
            item.setKeyInId(iqcRequestDto.getRequestedById());
            item.setUserId(iqcRequestDto.getRequestedById());
            item.setRequestNo(iqcRequestDto.getRequestNo());
        }

        List<IqcResults> iqcResults = records.stream().map(item -> modelMapper.map(item, IqcResults.class)).collect(Collectors.toList());

        List<IqcResults> data = this.iqcResultsRepo.saveAll(iqcResults);

        result.put("request", iqcRequest);
        result.put("data", iqcResults);


        return result;
    }

    /**
     * Tạo request Iqc (RECHECK)
     * Có 2 loại (Hàng 6 tháng check lại và hàng Sorting)
     * @param iqcRequestDto
     * @return
     */
    private Map<Object, Object> createIqcRequestRecheck(IqcRequestDto iqcRequestDto){
        Map<Object, Object> result = new HashMap<Object, Object>();
        iqcRequestDto.setRequestNo(this.generateIqcRequestNo());
        iqcRequestDto.setClassParam(iqcRequestDto.getClassParam());

        IqcRequest iqcRequest = this.iqcRequestRepo.save(this.modelMapper.map(iqcRequestDto, IqcRequest.class));


        List<IqcResultDto> records = this.iqcMapper.getPihStore(iqcRequestDto.getLotNos());
        for (IqcResultDto item : records) {
            item.setId(null);
            item.setClassParam(iqcRequestDto.getClassParam());
            item.setType(iqcRequestDto.getType());
            item.setUserId(iqcRequestDto.getRequestedById());
            item.setKeyInId(iqcRequestDto.getRequestedById());
            item.setRequestNo(iqcRequestDto.getRequestNo());
        }

        List<IqcResults> iqcResults = records.stream().map(item -> modelMapper.map(item, IqcResults.class)).collect(Collectors.toList());
        List<IqcResults> data = this.iqcResultsRepo.saveAll(iqcResults);

        result.put("request", iqcRequest);
        result.put("data", iqcResults);

        return result;
    }


}
