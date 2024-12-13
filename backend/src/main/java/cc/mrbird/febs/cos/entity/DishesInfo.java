package cc.mrbird.febs.cos.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 商品管理
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DishesInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 商品编号
     */
    private String code;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品描述
     */
    private String content;

    /**
     * 原料
     */
    private String rawMaterial;

    /**
     * 型号
     */
    private String portion;

    /**
     * 口味
     */
    private String taste;

    /**
     * 价格
     */
    private BigDecimal unitPrice;

    /**
     * 销量
     */
    private Integer saleNum;

    /**
     * 状态（0.下架 1.上架）
     */
    private String status;

    /**
     * 所属商家
     */
    private Integer merchantId;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 图片
     */
    private String images;

    /**
     * xx
     */
    private BigDecimal heat;

    /**
     * xxx
     */
    private BigDecimal protein;

    /**
     * xxxx
     */
    private BigDecimal fat;

    /**
     * 余量
     */
    private Integer laveNum;

    /**
     * 商品类型
     */
    private Integer typeId;

    @TableField(exist = false)
    private String merchantName;

}
