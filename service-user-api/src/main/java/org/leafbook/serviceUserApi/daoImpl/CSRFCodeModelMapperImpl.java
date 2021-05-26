package org.leafbook.serviceUserApi.daoImpl;

import org.leafbook.serviceUserApi.dao.CSRFCodeModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CSRFCodeModelMapperImpl {
    @Autowired
    private CSRFCodeModelMapper csrfCodeModelMapper;

    /**
     * 判断csrf是否符合要求
     * @param csrfCode
     * @return
     */
    public int selectDetectIsExist(String csrfCode) {
//        return csrfCodeModelMapper.selectById();
        return 1;
    }

    /**
     * 根据时间获取csrf
     * @param time
     * @return
     */
    public String selectCurrentCSRFCodeByTime(Long time) {
        Long start = time - 86400 * 1000 * 50L;
        return csrfCodeModelMapper.selectCurrentCSRFCodeByTime(start,time).get(0);
    }
}
