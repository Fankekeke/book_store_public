package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 商品收藏
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CollectInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 收藏用户
     */
    private Integer userId;

    /**
     * 商品ID
     */
    private Integer furnitureId;

    /**
     * 收藏时间
     */
    private String createDate;

    /**
     * 商家ID
     */
    private Integer merchantId;

    @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private String typeName;

    @TableField(exist = false)
    private String firnitureName;
}
