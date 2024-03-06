package com.intellicreation.api.controller;

import com.intellicreation.api.facade.MineFacade;
import com.intellicreation.common.ResponseResult;
import com.intellicreation.common.vo.PageVO;
import com.intellicreation.member.domain.dto.UpdateMineInfoDTO;
import com.intellicreation.member.domain.vo.*;
import com.intellicreation.member.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author za
 */
@RestController
@RequestMapping("/mine")
public class MineController {

    @Autowired
    private MineFacade mineFacade;

    @GetMapping("/mineId")
    public ResponseResult mineId() {
        Long id = SecurityUtils.getMemberId();
        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        return ResponseResult.okResult(result);
    }

    @GetMapping("/mineNavInfo")
    public ResponseResult mineNavInfo() {
        MineNavInfoVO mineNavInfoVO = mineFacade.mineNavInfo();
        return ResponseResult.okResult(mineNavInfoVO);
    }

    @GetMapping("/headerInfo")
    public ResponseResult headerInfo(Long memberId) {
        HeaderInfoVO headerInfoVO = mineFacade.headerInfo(memberId);
        return ResponseResult.okResult(headerInfoVO);
    }

    @GetMapping("/readMineCardInfo")
    public ResponseResult readMineCardInfo() {
        ReadMineCardInfoVO readMineCardInfoVO = mineFacade.readMineCardInfo();
        return ResponseResult.okResult(readMineCardInfoVO);
    }

    @GetMapping("/communityMineCardInfo")
    public ResponseResult communityMineCardInfo() {
        CommunityMineCardInfoVO communityMineCardInfoVO = mineFacade.communityMineCardInfo();
        return ResponseResult.okResult(communityMineCardInfoVO);
    }

    @GetMapping("/mineBasicInfo")
    public ResponseResult mineBasicInfo(@RequestParam Long memberId) {
        MineBasicInfoVO mineBasicInfoVO = mineFacade.mineBasicInfo(memberId);
        return ResponseResult.okResult(mineBasicInfoVO);
    }

    @GetMapping("/mineEditInfo")
    public ResponseResult mineInfo() {
        MineEditInfoVO mineEditInfoVO = mineFacade.mineEditInfo();
        return ResponseResult.okResult(mineEditInfoVO);
    }

    @PutMapping("/updateMineInfo")
    public ResponseResult updateMineInfo(@RequestBody UpdateMineInfoDTO updateMineInfoDTO) {
        mineFacade.updateMineInfo(updateMineInfoDTO);
        return ResponseResult.okResult();
    }

    @GetMapping("/mineArticle")
    public ResponseResult mineArticle(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "5") Integer pageSize) {
        PageVO pageVO = mineFacade.mineArticle(pageNum, pageSize);
        return ResponseResult.okResult(pageVO);
    }

//    @GetMapping("/mineFavorite")
//    public ResponseResult mineFavorite() {
//
//        return ResponseResult.okResult();
//    }
//
//    @GetMapping("/followList")
//    public ResponseResult followList() {
//
//        return ResponseResult.okResult();
//    }
//
//    @GetMapping("/browsingHistory")
//    public ResponseResult browsingHistory() {
//
//        return ResponseResult.okResult();
//    }
//
//    @GetMapping("/recentLike")
//    public ResponseResult recentLike() {
//
//        return ResponseResult.okResult();
//    }
//
//    @GetMapping("/loginLog")
//    public ResponseResult loginLog() {
//
//        return ResponseResult.okResult();
//    }
}
