package org.gin.response.fields;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * 约稿请求
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2022/10/13 15:53
 **/
@Data
public class CommissionRequest {
    /**
     * 作者uid
     */
    Long creatorUserId;
    /**
     * 约稿方uid
     */
    Long fanUserId;
    /**
     * 计划id
     */
    Long planId;

    PostWork postWork;
    /**
     * 请求id
     */
    Long requestId;
    /**
     * 请求完成DDL
     */
    ZonedDateTime requestPostDeadlineDatetime;
    /**
     * 请求创作的作品类型
     */
    String requestPostWorkType;
    /**
     * 价格
     */
    Long requestPrice;

    RequestProposal requestProposal;
    /**
     * 请求回复DDL
     */
    ZonedDateTime requestResponseDeadlineDatetime;

    /**
     * 请求状态
     */
    String requestStatus;
    /**
     * 标签
     */
    List<String> requestTags;

    String role;

    /**
     * 根据要求创作的作品
     */
    @Data
    static class PostWork {
        Long postWorkId;
        String postWorkType;
    }

    /**
     * 提议（约稿要求）
     */
    @Data
    static class RequestProposal {
        @JsonProperty("requestOriginalProposal")
        String requestOriginalProposal;
        @JsonProperty("requestOriginalProposalHtml")
        String requestOriginalProposalHtml;
        @JsonProperty("requestOriginalProposalLang")
        String requestOriginalProposalLang;
        List<RequestProposal> requestTranslationProposal;
    }
}
