package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.PostInfo;
import cc.mrbird.febs.cos.entity.ReplyInfo;
import cc.mrbird.febs.cos.entity.UserInfo;
import cc.mrbird.febs.cos.service.IPostInfoService;
import cc.mrbird.febs.cos.service.IReplyInfoService;
import cc.mrbird.febs.cos.service.IUserInfoService;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/reply-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReplyInfoController {

    private final IReplyInfoService replyInfoService;

    private final IPostInfoService postInfoService;

    private final IUserInfoService userInfoService;

    /**
     * 分页获取消息回复信息
     *
     * @param page      分页对象
     * @param replyInfo 消息回复信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ReplyInfo> page, ReplyInfo replyInfo) {
        return R.ok(replyInfoService.selectReplyPage(page, replyInfo));
    }

    /**
     * 消息回复信息详情
     *
     * @param id 消息回复ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(replyInfoService.getById(id));
    }

    /**
     * 消息回复信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(replyInfoService.list());
    }

    /**
     * 获取具体的帖子回复信息
     * @param postId
     * @return
     */
    @GetMapping("/list/{id}")
    public R replyListByPostId(@PathVariable(value = "id") Integer postId) {
        PostInfo postInfo = postInfoService.getById(postId);
        postInfoService.update(Wrappers.<PostInfo>lambdaUpdate().set(PostInfo::getPageviews, postInfo.getPageviews() + 1).eq(PostInfo::getId, postId));
        return R.ok(replyInfoService.replyListByPostId(postId));
    }

    /**
     * 添加回复信息
     * @param replyInfo
     * @return
     */
    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public R save(ReplyInfo replyInfo) {
        UserInfo userInfo = userInfoService.getOne(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getUserId, replyInfo.getUserId()));
        replyInfo.setUserId(Long.valueOf(userInfo.getId()));
        // 获取贴子信息
        PostInfo postInfo = postInfoService.getById(replyInfo.getPostId());
        replyInfo.setDeleteFlag(0);
        replyInfo.setSendCreate(DateUtil.formatDateTime(new Date()));
        return R.ok(replyInfoService.save(replyInfo));
    }

    /**
     * 修改消息回复信息
     *
     * @param replyInfo 消息回复信息
     * @return 结果
     */
    @PutMapping
    public R edit(ReplyInfo replyInfo) {
        return R.ok(replyInfoService.updateById(replyInfo));
    }

    /**
     * 删除消息回复信息
     *
     * @param ids ids
     * @return 消息回复信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(replyInfoService.removeByIds(ids));
    }
}
