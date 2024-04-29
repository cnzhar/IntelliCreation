package com.intellicreation.api.facade;

import com.intellicreation.article.domain.vo.MineArticleListVO;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.domain.dto.UpdateMineInfoDTO;
import com.intellicreation.member.domain.vo.*;

/**
 * @author za
 */
public interface MineFacade {

    /**
     * 获取顶部导航栏信息
     *
     * @return
     */
    MineNavInfoVO mineNavInfo();

    /**
     * 个人信息页顶部信息
     *
     * @return
     */
    HeaderInfoVO headerInfo(Long memberId);

    /**
     * 获取阅读页面“我的”卡片信息
     *
     * @return
     */
    ReadMineCardInfoVO readMineCardInfo();

    /**
     * 获取社区页面“我的”卡片信息
     *
     * @return
     */
    CommunityMineCardInfoVO communityMineCardInfo();

    /**
     * 个人基础信息
     *
     * @param memberId
     * @return
     */
    MineBasicInfoVO mineBasicInfo(Long memberId);

    /**
     * 修改个人信息页面，需要先获取信息
     *
     * @return
     */
    MineEditInfoVO mineEditInfo();

    /**
     * 用户修改个人信息
     *
     * @param updateMineInfoDTO
     */
    void updateMineInfo(UpdateMineInfoDTO updateMineInfoDTO);

    /**
     * 我的文章
     *
     * @return
     */
    PageVO mineArticle(Integer pageNum, Integer pageSize, Long memberId);

    /**
     * 用户获取登录日志
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageVO mineLoginLog(Integer pageNum, Integer pageSize);
}
