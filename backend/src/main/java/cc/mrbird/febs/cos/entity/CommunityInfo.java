package cc.mrbird.febs.cos.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 社区管理
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CommunityInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 社区名称
     */
    private String name;

    /**
     * 社区编号
     */
    private String code;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 经度
     */
    private BigDecimal longitude;

    /**
     * 纬度
     */
    private BigDecimal latitude;

    /**
     * 图片
     */
    private String images;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private String createDate;


}
