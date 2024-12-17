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
 * 图书管理
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
     * 图书编号
     */
    private String code;

    /**
     * 图书名称
     */
    private String name;

    /**
     * 图书描述
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
     * 图书类型
     */
    private Integer typeId;


    /**
     * 作者姓名
     */
    private String author;

    /**
     * 图书的ISBN号，用于唯一标识一本书
     */
    private String isbn;

    /**
     * 图书的标题
     */
    private String title;

    /**
     * 作者的简介
     */
    private String authorIntro;

    /**
     * 图书的标签，用于分类或标识图书的主题
     */
    private String tag;

    /**
     * 评价人数
     */
    private String numraters;

    /**
     * 平均评分
     */
    private String average;

    /**
     * 图书的装帧类型
     */
    private String binding;

    /**
     * 图书的页数
     */
    private String pages;

    /**
     * 出版社名称
     */
    private String publisher;

    /**
     * 原作标题，对于翻译书籍，可能与实际标题不同
     */
    private String originTitle;

    /**
     * 图书详情页面的URL
     */
    private String url;

    /**
     * 图书封面图片的URL
     */
    private String image;

    /**
     * 图书的摘要或简介
     */
    private String summary;

    @TableField(exist = false)
    private String merchantName;

}
