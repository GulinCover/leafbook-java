//package org.leafbook.api.modelApi.common;
//
//import com.baomidou.mybatisplus.annotation.FieldFill;
//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.annotation.Version;
//import lombok.Data;
//
//import java.io.Serializable;
//import java.util.Date;
//
//@Data
//public class Model implements Serializable {
//    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
//    public Long updateTime;
//    @TableField(value = "create_time", fill = FieldFill.INSERT)
//    public Long createTime;
//    @Version
//    public Integer version;
//    public Integer isBlack;
//}

/**
 * ====================================================
 */

package org.leafbook.api.modelApi.common;

import lombok.Data;
import java.io.Serializable;

@Data
public class Model implements Serializable {
    public Long updateTime;
    public Long createTime;
    public Integer version;
    public Integer isBlack;
}

