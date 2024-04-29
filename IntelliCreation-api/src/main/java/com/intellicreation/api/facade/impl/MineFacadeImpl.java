package com.intellicreation.api.facade.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.intellicreation.api.facade.MineFacade;
import com.intellicreation.article.domain.entity.AmsArticleDO;
import com.intellicreation.article.domain.vo.MineArticleListVO;
import com.intellicreation.article.service.AmsArticleService;
import com.intellicreation.article.service.AmsArticleTagRelationService;
import com.intellicreation.article.service.AmsTagService;
import com.intellicreation.common.util.BeanCopyUtils;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.domain.dto.UpdateMineInfoDTO;
import com.intellicreation.member.domain.entity.UmsMemberDO;
import com.intellicreation.member.domain.vo.*;
import com.intellicreation.member.service.UmsMemberLoginLogService;
import com.intellicreation.member.service.UmsMemberService;
import com.intellicreation.member.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author za
 */
@Component
public class MineFacadeImpl implements MineFacade {

    @Autowired
    private UmsMemberService umsMemberService;

    @Autowired
    private AmsArticleService amsArticleService;

    @Autowired
    private AmsTagService amsTagService;

    @Autowired
    private AmsArticleTagRelationService amsArticleTagRelationService;

    @Autowired
    private UmsMemberLoginLogService umsMemberLoginLogService;

    @Override
    public MineNavInfoVO mineNavInfo() {
        Long memberId = SecurityUtils.getMemberId();
        UmsMemberDO umsMemberDO = umsMemberService.getById(memberId);
        MineNavInfoVO mineNavInfoVO = BeanCopyUtils.copyBean(umsMemberDO, MineNavInfoVO.class);
        return mineNavInfoVO;
    }

    @Override
    public HeaderInfoVO headerInfo(Long memberId) {
        return umsMemberService.headerInfo(memberId);
    }

    @Override
    public ReadMineCardInfoVO readMineCardInfo() {
        Long memberId = SecurityUtils.getMemberId();
        return umsMemberService.readMineCardInfo(memberId);
    }

    @Override
    public CommunityMineCardInfoVO communityMineCardInfo() {
        Long memberId = SecurityUtils.getMemberId();
        return umsMemberService.communityMineCardInfo(memberId);
    }

    @Override
    public MineBasicInfoVO mineBasicInfo(Long memberId) {
        return umsMemberService.mineBasicInfo(memberId);
    }

    @Override
    public MineEditInfoVO mineEditInfo() {
        Long memberId = SecurityUtils.getMemberId();
        return umsMemberService.mineEditInfo(memberId);
    }

    @Override
    public void updateMineInfo(UpdateMineInfoDTO updateMineInfoDTO) {
        Long memberId = SecurityUtils.getMemberId();
        updateMineInfoDTO.setId(memberId);
        umsMemberService.updateMineInfo(updateMineInfoDTO);
    }

    @Override
    public PageVO mineArticle(Integer pageNum, Integer pageSize, Long memberId) {
        // 查询条件
        LambdaQueryWrapper<AmsArticleDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(AmsArticleDO::getId, AmsArticleDO::getTitle, AmsArticleDO::getSummary,
                        AmsArticleDO::getCategory1Id, AmsArticleDO::getThumbnail, AmsArticleDO::getLikeCount,
                        AmsArticleDO::getViewCount, AmsArticleDO::getGmtCreate)
                .eq(AmsArticleDO::getCreateBy, memberId);
        // 分页查询
        Page<AmsArticleDO> page = new Page<>(pageNum, pageSize);
        amsArticleService.page(page, lambdaQueryWrapper);
        // 封装查询结果
        List<MineArticleListVO> mineArticleListVOList = BeanCopyUtils.copyBeanList(page.getRecords(), MineArticleListVO.class);
        mineArticleListVOList
                // 查询关联的标签
                .forEach(mineArticleListVO -> {
                    List<Long> tagIds = amsArticleTagRelationService.getTagByArticleId(mineArticleListVO.getId());
                    List<String> tagNameList = new ArrayList<>();
                    for (Long tagId : tagIds) {
                        String tagName = amsTagService.getById(tagId).getName();
                        tagNameList.add(tagName);
                    }
                    mineArticleListVO.setTagName(tagNameList);
                });
        return new PageVO(mineArticleListVOList, page.getTotal());
    }

    @Override
    public PageVO mineLoginLog(Integer pageNum, Integer pageSize) {
        Long memberId = SecurityUtils.getMemberId();
        return umsMemberLoginLogService.getMemberLoginLog(pageNum, pageSize, memberId);
    }
}
