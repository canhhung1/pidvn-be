package pidvn.modules.hr.waste_mng.services;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.*;
import pidvn.mappers.one.hr.waste_mng.WasteMngMapper;
import pidvn.modules.hr.waste_mng.models.WasteDataVo;
import pidvn.modules.hr.waste_mng.models.WasteSearchVo;
import pidvn.modules.hr.waste_mng.utils.FileUploadUtil;
import pidvn.repositories.one.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
public class WasteMngService implements IWasteMngService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private WasteMasterDataRepo masterDataRepo;

    @Autowired
    private WasteDetailDataRepo detailDataRepo;

    @Autowired
    private WasteGroupRepo wasteGroupRepo;

    @Autowired
    private WasteTypeRepo wasteTypeRepo;

    @Autowired
    private WasteHandleCompanyRepo handleCompanyRepo;

    @Autowired
    private WasteDetailImageRepo detailImageRepo;

    @Autowired
    private WasteImageRepo wasteImageRepo;

    @Autowired
    private WastePriceRepo wastePriceRepo;

    @Autowired
    private WasteMngMapper wasteMngMapper;

    @Override
    public List<Users> getUsers() {
        return this.usersRepo.findAll();
    }

    @Override
    public WasteMasterData createWasteMasterData(WasteMasterData masterData) {
        return this.masterDataRepo.save(masterData);
    }

    @Override
    public Map createWasteDetailData(WasteDataVo dataVo) {

        WasteDetailData detailData = new WasteDetailData();
        detailData.setWasteMaster(dataVo.getWasteMaster());
        detailData.setWasteGroup(dataVo.getWasteGroup());
        detailData.setWasteType(dataVo.getWasteType());
        detailData.setWeight(dataVo.getWeight());
        detailData.setNetWeight(dataVo.getNetWeight());
        detailData.setSealNo(dataVo.getSealNo());
        detailData.setPackagingNo(dataVo.getPackagingNo());
        detailData.setCreatedBy(dataVo.getCreatedBy());

        WasteDetailData detail = this.detailDataRepo.save(detailData);

        List<WasteDetailImage> images = new ArrayList<>();

        for (Integer image : dataVo.getImages()) {
            WasteDetailImage detailImage = new WasteDetailImage();
            detailImage.setWasteDetail(detail.getId());
            detailImage.setWasteImage(image);
            images.add(detailImage);
        }

        List<WasteDetailImage> imageSave = this.detailImageRepo.saveAll(images);

        Map result = new HashMap();
        result.put("wasteDetail", detail);
        result.put("images", imageSave);

        return result;
    }

    @Override
    public List<WasteDataVo> getWasteMasterData(WasteSearchVo searchVo) {
        return this.wasteMngMapper.getWasteMasterData(searchVo);
    }

    @Override
    public List<WasteDataVo> getWasteDetailData(WasteSearchVo searchVo) {
        return this.wasteMngMapper.getWasteDetailData(searchVo);
    }

    @Override
    public List<WasteDataVo> getWasteDetailDataSummary(WasteSearchVo searchVo) {
        return this.wasteMngMapper.getWasteDetailDataSummary(searchVo);
    }

    @Override
    public List<WasteDataVo> getWasteDetailDataSummaryAll(WasteSearchVo searchVo) {
        return this.wasteMngMapper.getWasteDetailDataSummaryAll(searchVo);
    }

    @Override
    public List<WasteGroup> getWasteGroup() {
        return this.wasteGroupRepo.findAll();
    }

    @Override
    public List<WasteType> getWasteType(Integer group) {
        return this.wasteTypeRepo.findByWasteGroup(group);
    }

    @Override
    public List<WasteHandleCompany> getHandleCompany() {
        return this.handleCompanyRepo.findAll();
    }

    @Override
    public WasteImage uploadImage(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        String uploadDir = "../FileRepository/pidvn-be/waste-mng/";

        FileUploadUtil.saveFile(uploadDir, fileName, file);
        WasteImage wasteImage = new WasteImage();
        wasteImage.setUrl(uploadDir.replaceAll("\\.", "") + fileName);
        return this.wasteImageRepo.save(wasteImage);
    }

    @Override
    public Integer removeImage(Integer id) throws Exception {
        WasteImage image = this.wasteImageRepo.getById(id);
        File file = new File(".." + image.getUrl());
        if (file.delete() == false) {
            throw new Exception("Có lỗi không thể xóa ảnh");
        }

        this.wasteImageRepo.delete(image);
        return id;
    }

    @Override
    public byte[] exportMasterData(Integer masterId) {

        WasteMasterData master = this.masterDataRepo.getById(masterId);

        WasteHandleCompany company = this.handleCompanyRepo.getById(master.getHandleCompany());

        String reportPath = "P:\\IS\\CanhHung\\JasperReport\\HR\\WasteManagement";
        WasteSearchVo searchVo = new WasteSearchVo();
        searchVo.setWasteMaster(masterId);
        List<WasteDataVo> dataList = null;

        // Compile the Jasper report from .jrxml to .japser
        JasperReport jasperReport = null;
        try {

            // Chất thải nguy hại
            if (master.getWasteGroup() == 4) {
                dataList = this.wasteMngMapper.getWasteDetailDataSummary(searchVo);
                jasperReport = JasperCompileManager.compileReport(reportPath + "\\BBBG-CTNH.jrxml");
            }
            // Chất thải Kim loại giá trị
            else if (master.getWasteGroup() == 3) {
                searchVo.setWasteGroup(3);
                searchVo.setSqlType("Report");
                dataList = this.wasteMngMapper.getWasteDetailData(searchVo);
                jasperReport = JasperCompileManager.compileReport(reportPath + "\\BBBG-CTKLGT.jrxml");
            } else {
                dataList = this.wasteMngMapper.getWasteDetailDataSummary(searchVo);
                jasperReport = JasperCompileManager.compileReport(reportPath + "\\BBBG.jrxml");
            }

        } catch (JRException e) {
            e.printStackTrace();
            System.out.printf("ERROR" + e.toString());
        }

        // Get your data source
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

        // Add parameters
        Map<String, Object> parameters = new HashMap<>();

        String title = "";
        String wasteName = "";
        if (master.getWasteGroup() == 1) {
            title = "BIÊN BẢN BÀN GIAO CTRSH, CTRCN THÔNG THƯỜNG";
            wasteName = "CTRSH, CTRCN";
        } else if (master.getWasteGroup() == 2) {
            title = "BIÊN BẢN BẢN GIAO CTRTT TÁI CHẾ";
            wasteName = "CTRTT";
        } else if (master.getWasteGroup() == 3) {
            title = "BIÊN BẢN BẢN GIAO CHẤT THẢI KIM LOẠI GIÁ TRỊ";
            wasteName = "";
        } else if (master.getWasteGroup() == 4) {
            title = "BIÊN BẢN BẢN GIAO CHẤT THẢI NGUY HẠI";
            wasteName = "CHẤT THẢI NGUY HẠI";
        }

        parameters.put("title", title);
        parameters.put("wasteName", wasteName);
        parameters.put("handleCompanyName", company.getName());
        parameters.put("handleCompanyAddress1", company.getAddress1());
        parameters.put("handleCompanyAddress2", company.getAddress2());
        parameters.put("handleCompanyPhone1", company.getPhone1());
        parameters.put("handleCompanyPhone2", company.getPhone2());
        // Fill the report

        byte[] result = new byte[0];
        try {
            result = JasperExportManager.exportReportToPdf(JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource));
        } catch (JRException e) {
            e.printStackTrace();
            System.out.printf("EEEEEE =======>>" + e.toString());
        }

        return result;
    }

    @Override
    public byte[] exportChungTu(Integer masterId) throws JRException {
        WasteMasterData master = this.masterDataRepo.getById(masterId);
        WasteHandleCompany company = this.handleCompanyRepo.getById(master.getHandleCompany());
        String reportPath = "P:\\IS\\CanhHung\\JasperReport\\HR\\WasteManagement";
        WasteSearchVo searchVo = new WasteSearchVo();
        searchVo.setWasteMaster(masterId);
        List<WasteDataVo> dataList = this.wasteMngMapper.getWasteDetailDataSummary(searchVo);

        // Compile the Jasper report from .jrxml to .japser
        JasperReport jasperReport = null;
        try {
            jasperReport = JasperCompileManager.compileReport(reportPath + "\\ChungTu_CTNH.jrxml");
        } catch (JRException e) {
            e.printStackTrace();
            System.out.printf("EEEEEE =======>>" + e.toString());
        }

        // Get your data source
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

        // Add parameters
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("handleCompanyName", company.getName());
        parameters.put("handleCompanyAddress1", company.getAddress1());
        parameters.put("handleCompanyAddress2", company.getAddress2());
        parameters.put("handleCompanyPhone1", company.getPhone1());
        parameters.put("handleCompanyPhone2", company.getPhone2());

        byte[] result = new byte[0];
        try {
            result = JasperExportManager.exportReportToPdf(JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource));
        } catch (JRException e) {
            e.printStackTrace();
            System.out.printf("EEEEEE =======>>" + e.toString());
        }
        return result;
    }

    @Override
    public List<WasteDetailData> setupUnitPrice(Integer wasteMaster) {

        // B1. Lấy danh sách đơn giá
        List<WastePrice> priceList = this.wastePriceRepo.findAll();

        // B2. Tìm dữ liệu theo wasteMaster
        List<WasteDetailData> detailList = this.detailDataRepo.findByWasteMaster(wasteMaster);

        // B3. Set đơn giá vào wasteDetail
        for (WasteDetailData detail : detailList) {
            for (WastePrice price : priceList) {
                if (detail.getWasteType() == price.getWasteType()) {
                    detail.setPrice(price.getPrice());
                    break;
                }
            }
            this.detailDataRepo.save(detail);
        }

        return detailList;
    }

    @Override
    public void removeWasteMasterData(Integer id) {
        this.masterDataRepo.deleteById(id);
    }

    @Override
    public void removeWasteDetailData(Integer id) {
        this.detailDataRepo.deleteById(id);
    }

    @Override
    public ByteArrayInputStream exportExcel(WasteSearchVo searchVo) throws Exception {

        WasteMasterData masterData = this.masterDataRepo.findById(searchVo.getWasteMaster()).get();

        ByteArrayInputStream result = null;

        switch (masterData.getWasteGroup()) {
            case 3:
                result = this.exportCTKLGT(searchVo);
                break;
            default:
                throw new Exception("Chức năng đang phát triển !");
        }

        return result;

    }

    private ByteArrayInputStream exportCTKLGT(WasteSearchVo searchVo) throws IOException {

        List<WasteDataVo> data = this.getWasteDetailData(searchVo);

        String sourcePath = "Y:\\Public\\CanhHung\\Project\\WasteManager\\CTKLGT.xlsx";
        String targetPath = "Y:\\Public\\CanhHung\\Project\\WasteManager\\CTKLGT-temp.xlsx";

        File source = new File(sourcePath);
        File target = new File(targetPath);
        FileUtils.copyFile(source, target);

        File file = new File(targetPath);
        FileInputStream inputStream = new FileInputStream(file);

        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        int rowNum = 1;
        for (WasteDataVo item : data) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.getRowNum());
            row.createCell(1).setCellValue(item.getWasteTypeName());
            row.createCell(2).setCellValue(item.getWeight());
            row.createCell(3).setCellValue(item.getNetWeight());
            row.createCell(4).setCellValue(item.getSealNo());
            row.createCell(5).setCellValue(item.getPackagingNo());
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        ByteArrayInputStream result = new ByteArrayInputStream(out.toByteArray());

        // Close the workbook and output stream
        workbook.close();
        Files.deleteIfExists(Paths.get(targetPath));
        return result;
    }

}
