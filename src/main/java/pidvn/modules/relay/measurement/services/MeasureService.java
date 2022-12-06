package pidvn.modules.relay.measurement.services;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.*;
import pidvn.mappers.one.relay.measurement.MeasureMapper;
import pidvn.modules.relay.measurement.models.*;
import pidvn.modules.relay.measurement.utils.FileUploadUtil;
import pidvn.repositories.one.*;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class MeasureService implements IMeasureService {

    @Autowired
    ServletContext servletContext;

    @Autowired
    private MeaStandardRepo standardRepo;

    @Autowired
    private MeaItemRepo itemRepo;

    @Autowired
    private MeaMasterDataRepo masterDataRepo;

    @Autowired
    private MeaDetailDataRepo detailDataRepo;

    @Autowired
    private MeasureMapper measureMapper;

    @Autowired
    private MeaItemImageRepo itemImageRepo;

    @Autowired
    private MeaChildItemRepo childItemRepo;

    @Override
    public List<MeasureItemVo> getItems() {
        return this.measureMapper.getItems();
    }

    @Override
    public Optional<MeaItem> getItemById(Integer id) {
        return this.itemRepo.findById(id);
    }

    @Override
    public Optional<MeaItem> getItems(Integer id) {
        return this.itemRepo.findById(id);
    }

    @Override
    public List<MeaStandard> getStandard(Integer item, String modelType) {
        return this.standardRepo.findByItemAndModelType(item, modelType);
    }

    @Override
    public List<MeasureDataVo> saveMeasureData(List<MeasureDataVo> dataVoList) {

        // B1: Lưu master data
        MeaMasterData masterData = this.saveMasterData(dataVoList.get(0));

        // B2: Lưu detail data
        List<MeaDetailData> detailDataList = this.saveDetailDataList(dataVoList, masterData);

        // B3: Lấy data vừa lưu
        List<MeasureDataVo> result = this.measureMapper.getMeasureDataByMasterId(masterData.getId());

        return result;
    }

    @Override
    public List<MeaItemImage> getItemImages(Integer item) {
        return this.itemImageRepo.findByItem(item);
    }

    @Override
    public List<MeaItemImage> getItemImages(Integer item, String modelType) {
        return this.itemImageRepo.findByItemAndModelType(item, modelType);
    }

    @Override
    public List<MeasureMasterDataVo> getMasterData(MeasureSearchVo searchVo) {
        List<MeasureMasterDataVo> result = this.measureMapper.getMasterData(searchVo);
        return result;
    }

    @Override
    public List<MeasureDetailDataVo> getDetailData(MeasureSearchVo searchVo) {
        List<MeasureDetailDataVo> result = this.measureMapper.getDetailData(searchVo);
        return result;
    }

    @Override
    public MeaMasterData approveData(Integer masterId, String username) {

        Optional<MeaMasterData> master = this.masterDataRepo.findById(masterId);
        if (!master.isPresent()) {
            return null;
        }
        master.get().setApprovedBy(username);
        MeaMasterData result = this.masterDataRepo.save(master.get());
        return result;
    }

    @Override
    public List<MeaMasterData> quickApprove(List<MeaMasterData> masterDataList, String approver) {

        List<Integer> ids = new ArrayList<>();

        for (MeaMasterData master : masterDataList) {
            ids.add(master.getId());
        }

        int result = this.measureMapper.quickApprove(ids, approver);

        return masterDataList;
    }

    @Override
    public List<MeaDetailData> updateDetailData(List<MeaDetailData> detailList) {
        return this.detailDataRepo.saveAll(detailList);
    }

    @Override
    public List<MeaChildItem> getChildItems(Integer item, String modelType) {
        return this.childItemRepo.findByItemAndModelType(item, modelType);
    }

    @Override
    public MeaItemImage uploadImage(MultipartFile file, Integer item, String modelType) throws IOException {

        String fileName = item + "_" + modelType + "_" + UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        String uploadDir = "../FileRepository/pidvn-be/measurement/";

        FileUploadUtil.saveFile(uploadDir, fileName, file);

        MeaItemImage meaItemImage = new MeaItemImage();
        meaItemImage.setItem(item);
        meaItemImage.setModelType(modelType);
        meaItemImage.setImageUrl(uploadDir.replaceAll("\\.","") + fileName);

        return this.itemImageRepo.save(meaItemImage);
    }

    @Override
    public void removeImage(Integer id) {
        MeaItemImage image = this.itemImageRepo.getById(id);



        File file = new File(".." + image.getImageUrl());

        if (file.delete() == false) {
            System.out.println("Deleted the file: " + file.getName());
        }

        this.itemImageRepo.delete(image);
    }

    private MeaMasterData saveMasterData(MeasureDataVo dataVo) {

        MeaMasterData masterData = new MeaMasterData();
        masterData.setLine(dataVo.getLine());
        masterData.setItem(dataVo.getItem());
        masterData.setModelType(dataVo.getModelType());
        masterData.setModel(dataVo.getModel());
        masterData.setShift(dataVo.getShift());
        masterData.setReason(dataVo.getReason());
        masterData.setUser(dataVo.getUser());
        masterData.setQty(dataVo.getQty());
        masterData.setMold(dataVo.getMold());
        masterData.setNote(dataVo.getNote());
        masterData.setQaCard(dataVo.getQaCard());

        return this.masterDataRepo.save(masterData);
    }

    private List<MeaDetailData> saveDetailDataList(List<MeasureDataVo> dataVoList, MeaMasterData masterData) {

        List<MeaDetailData> detailList = new ArrayList<>();

        for (MeasureDataVo dataVo : dataVoList) {
            MeaDetailData detailData = new MeaDetailData();
            detailData.setMaster(masterData.getId());
            detailData.setItem(masterData.getItem());
            detailData.setChildItem(dataVo.getChildItem());
            detailData.setA1(dataVo.getA1());
            detailData.setB1(dataVo.getB1());
            detailData.setB2(dataVo.getB2());
            detailData.setC1(dataVo.getC1());
            detailData.setC2(dataVo.getC2());
            detailData.setC3(dataVo.getC3());
            detailData.setD1(dataVo.getD1());
            detailData.setD2(dataVo.getD2());
            detailData.setD3(dataVo.getD3());
            detailData.setD4(dataVo.getD4());
            detailData.setE1(dataVo.getE1());
            detailData.setE2(dataVo.getE2());
            detailData.setE3(dataVo.getE3());
            detailData.setE4(dataVo.getE4());
            detailData.setE5(dataVo.getE5());
            detailData.setCreatedAt(masterData.getCreatedAt());
            detailData.setUpdatedAt(masterData.getUpdatedAt());
            detailList.add(detailData);
        }

        return this.detailDataRepo.saveAll(detailList);
    }
}























