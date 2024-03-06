package com.intellicreation.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.intellicreation.article.domain.entity.AmsArticleTagRelationDO;
import com.intellicreation.article.mapper.AmsArticleTagRelationMapper;
import com.intellicreation.article.service.AmsArticleTagRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author za
 * @since 2024-03-05
 */
@Service
public class AmsArticleTagRelationServiceImpl extends ServiceImpl<AmsArticleTagRelationMapper, AmsArticleTagRelationDO> implements AmsArticleTagRelationService {

    @Override
    public void saveArticleTag(Long articleId, List<Long> tag) {
        List<AmsArticleTagRelationDO> amsArticleTagRelationDOList = tag.stream()
                .map(tagId -> {
                    AmsArticleTagRelationDO amsArticleTagRelationDO = new AmsArticleTagRelationDO();
                    amsArticleTagRelationDO.setArticleId(articleId);
                    amsArticleTagRelationDO.setTagId(tagId);
                    return amsArticleTagRelationDO;
                })
                .collect(Collectors.toList());
        // 保存关联关系列表
        saveBatch(amsArticleTagRelationDOList);
    }

    @Override
    public List<Long> getTagByArticleId(Long id) {
        LambdaQueryWrapper<AmsArticleTagRelationDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(AmsArticleTagRelationDO::getTagId)
                .eq(AmsArticleTagRelationDO::getArticleId, id);
        List<AmsArticleTagRelationDO> amsArticleTagRelationDOList = list(lambdaQueryWrapper);
        return amsArticleTagRelationDOList.stream()
                .map(AmsArticleTagRelationDO::getTagId)
                .collect(Collectors.toList());
    }
}
