package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.PostInfo;
import cc.mrbird.febs.cos.entity.UserInfo;
import cc.mrbird.febs.cos.service.IPostInfoService;
import cc.mrbird.febs.cos.service.IUserInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/post-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostInfoController {

    private final IPostInfoService postInfoService;

    private final IUserInfoService userInfoService;

    /**
     * 分页获取贴子信息
     *
     * @param page     分页对象
     * @param postInfo 贴子信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<PostInfo> page, PostInfo postInfo) {
        return R.ok(postInfoService.selectPostPage(page, postInfo));
    }

    /**
     * 获取模块下的贴子
     *
     * @param tagId
     * @return
     */
    @GetMapping("/tag/{tagId}")
    public R getPostByTag(@PathVariable("tagId") Integer tagId) {
        return R.ok(postInfoService.getPostByTag(tagId));
    }

    /**
     * 获取贴子详细信息
     *
     * @param postId
     * @return
     */
    @GetMapping("/{postId}")
    public R postDetail(@PathVariable("postId") Integer postId) {
        return R.ok(postInfoService.postDetail(postId));
    }

    /**
     * 模糊查询帖子信息
     *
     * @return
     */
    @GetMapping("/list/{key}")
    public R list(@PathVariable("key") String key) {
        return R.ok(postInfoService.postByKey(key));
    }

    /**
     * 贴子信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(postInfoService.list());
    }

    /**
     * 新增贴子信息
     *
     * @param postInfo 贴子信息
     * @return 结果
     */
    @PostMapping
    public R save(PostInfo postInfo) {
        // 获取用户信息
        UserInfo userInfo = userInfoService.getOne(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getUserId, postInfo.getUserId()));
        if (userInfo != null) {
            postInfo.setUserId(Long.valueOf(userInfo.getId()));
        }
        postInfo.setPageviews(0);
        postInfo.setDeleteFlag(0);
        postInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(postInfoService.save(postInfo));
    }

    /**
     * 修改贴子信息
     *
     * @param postInfo 贴子信息
     * @return 结果
     */
    @PutMapping
    public R edit(PostInfo postInfo) {
        return R.ok(postInfoService.updateById(postInfo));
    }

    /**
     * 删除贴子信息
     *
     * @param ids ids
     * @return 贴子信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(postInfoService.removeByIds(ids));
    }
}
