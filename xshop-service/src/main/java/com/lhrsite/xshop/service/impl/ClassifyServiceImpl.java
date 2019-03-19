package com.lhrsite.xshop.service.impl;

import com.lhrsite.xshop.core.exception.ErrEumn;
import com.lhrsite.xshop.core.exception.XShopException;
import com.lhrsite.xshop.core.utils.IdentifyUtil;
import com.lhrsite.xshop.core.utils.MultipartFileUtil;
import com.lhrsite.xshop.mapper.ClassifyMapper;
import com.lhrsite.xshop.po.Classify;
import com.lhrsite.xshop.po.QClassify;
import com.lhrsite.xshop.repository.ClassifyRepository;
import com.lhrsite.xshop.service.ClassifyService;
import com.lhrsite.xshop.vo.ClassifyPriceRange;
import com.lhrsite.xshop.vo.ClassifyVO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClassifyServiceImpl extends BaseServiceImpl implements ClassifyService {

    private final ClassifyRepository classifyRepository;
    private JPAQueryFactory queryFactory;
    private final ClassifyMapper classifyMapper;

    @Value("${app.upload.classify.pictures}")
    private String uploadPicturePath;

    @Autowired
    public ClassifyServiceImpl(EntityManager entityManager, ClassifyRepository classifyRepository,
                               ClassifyMapper classifyMapper) {
        super(entityManager);
        this.classifyRepository = classifyRepository;
        this.classifyMapper = classifyMapper;
        queryFactory = getQueryFactory();
    }

    @Override
    public List<ClassifyVO> getClassifyTree(Integer eid) {

        List<Classify> classifies = classifyMapper.findAllClassify(eid);

        List<ClassifyVO> classifyVOS = ClassifyVO.init(classifies);

        List<ClassifyVO> resultVO = new ArrayList<>();

        classifyToVO(classifyVOS, resultVO);

        return resultVO;
    }

    @Override
    public List<ClassifyVO> getFClassify(Integer fid, Integer eid) {

        List<Classify> classifies = classifyMapper.findClassifyByFid(fid, eid);
        List<ClassifyVO> classifyVOS = ClassifyVO.init(classifies);
        return classifyVOS;
    }

    private void classifyToVO(List<ClassifyVO> classifyVOS, List<ClassifyVO> resultVO) {
        classifyVOS.forEach(classifyVO -> {
            if (classifyVO.getClGrade() == 0) {
                resultVO.add(classifyVO);
            } else {
                classifyVOS.forEach(classifyVO1 -> {
                    if (classifyVO1.getClId().equals(classifyVO.getClFid())) {
                        classifyVO1.getChildren().add(classifyVO);
                    }
                });
            }

        });
    }

    @Override
    public Classify add(Classify classify) throws XShopException {
        // 判断是否存在
        QClassify qClassify = QClassify.classify;

        Classify existClassify = queryFactory.selectFrom(qClassify)
                .where(qClassify.clName.eq(classify.getClName())).fetchOne();

        if (existClassify != null) {
            throw new XShopException(ErrEumn.CLASSIFY_IS_EXIST);
        }
        classify.setClSerial(0);
        return classifyRepository.save(classify);
    }

    @Override
    public Classify update(Classify classify) {
        return classifyRepository.save(classify);
    }


    @Override
    public Classify findById(Integer clId) throws XShopException {
        Optional<Classify> classifyOptional = classifyRepository.findById(clId);
        if (!classifyOptional.isPresent()) {
            throw new XShopException(ErrEumn.CLASSIFY_IS_NOTEXIST);
        }
        return classifyOptional.get();
    }

    private List<Classify> findByFid(Integer fid) throws XShopException {
        return classifyRepository.findAllByClFid(fid);
    }

    @Override
    public void del(Integer clId, Integer eid) throws XShopException {
        classifyMapper.delClassify(clId, eid);
    }

    @Override
    public List<ClassifyPriceRange> getClassifyPriceRange(Integer fid, Integer eid) {
        return classifyMapper.getClassifyPriceRange(fid, eid);
    }

    @Override
    public String uploadClassifyPicture(MultipartFile multipartFile) throws IOException {

        String newFileName = IdentifyUtil.getIdentify() + MultipartFileUtil.getFileType(multipartFile);
        System.out.println(newFileName);
        File file = new File(uploadPicturePath + newFileName);

        IOUtils.copy(multipartFile.getInputStream(), new FileOutputStream(file));
        return file.getName();
    }

    @Override
    public void getClassifyPicture(String pictureName, HttpServletResponse response) throws IOException {
        File file = new File(uploadPicturePath + pictureName);
        response.setContentType("image/*");
        response.setCharacterEncoding("utf8");
        IOUtils.copy(new FileInputStream(file), response.getOutputStream());
    }
}
