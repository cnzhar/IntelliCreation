package com.intellicreation.api.facade;

import com.intellicreation.article.domain.dto.PostQueryParamDTO;
import com.intellicreation.common.vo.PageVO;

/**
 * @author za
 */
public interface CommunityManagementFacade {

    PageVO postList(Integer pageNum, Integer pageSize, PostQueryParamDTO postQueryParamDTO);

    void deletePost(Long id);
}
