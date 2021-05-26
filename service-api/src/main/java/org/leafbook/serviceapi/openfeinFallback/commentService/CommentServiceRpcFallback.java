package org.leafbook.serviceapi.openfeinFallback.commentService;

import feign.hystrix.FallbackFactory;
import org.leafbook.api.modelApi.commentInfo.CommentModel;
import org.leafbook.serviceapi.serviceRpc.commentService.CommentServiceRpc;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceRpcFallback implements FallbackFactory<CommentServiceRpc> {
    @Override
    public CommentServiceRpc create(Throwable cause) {
        return new CommentServiceRpc() {
            @Override
            public List<CommentModel> getSelectMultiComment1InfoRpc(Long topicId, Long page) {
                return null;
            }

            @Override
            public int postPublicComment1InfoRpc(Long userId, Long topicId, String comment1Content) {
                return 0;
            }

            @Override
            public int postAddEntryInfoForComment1InfoRpc(Long userId, Long comment1Id, Long entryId) {
                return 0;
            }

            @Override
            public List<Long> getSelectEntryInfoForComment1InfoRpc(Long comment1Id) {
                return null;
            }

            @Override
            public List<CommentModel> getSelectMultiComment2InfoRpc(Long comment1Id, Long page) {
                return null;
            }

            @Override
            public int postPublicComment2InfoRpc(Long userId, Long topicId, Long comment1Id, Long commentUserId, String comment2Content) {
                return 0;
            }

            @Override
            public int postTouchStarComment1InfoRpc(Long comment1Id) {
                return 0;
            }

            @Override
            public int postTouchTreadComment1InfoRpc(Long comment1Id) {
                return 0;
            }

            @Override
            public List<CommentModel> getSelectRandomComment1InfoRpc(Long topicId, Integer randomNumber) {
                return null;
            }

            @Override
            public List<Long> getSelectMultiEntryIdsByComment1IdRpc(Long comment1Id) {
                return null;
            }

            @Override
            public int postUpdateComment1InfoTouchStarAmountRpc(Long comment1Id) {
                return 0;
            }

            @Override
            public int postUpdateComment1InfoTouchTreadAmountRpc(Long comment1Id) {
                return 0;
            }

            @Override
            public List<CommentModel> getSelectMultiTalkInfoRpc(Long topicId, Long page) {
                return null;
            }

            @Override
            public List<CommentModel> getSelectMultiTalkComment1InfoRpc(Long talkId, Long page) {
                return null;
            }

            @Override
            public List<CommentModel> getSelectMultiTalkComment2InfoRpc(Long talkComment1Id, Long page) {
                return null;
            }

            @Override
            public Long postPublicTalkInfoRpc(Long topicId, Long userId, String talkTitle, String talkDesc) {
                return null;
            }

            @Override
            public int postPublicTalkComment1InfoRpc(Long talkId, Long userId, String talkCommentContent) {
                return 0;
            }

            @Override
            public int postPublicTalkComment2InfoRpc(Long talkComment1Id, Long userId, String talkComment2Content) {
                return 0;
            }

            @Override
            public int postTouchStarTalkInfoRpc(Long talkId) {
                return 0;
            }

            @Override
            public int postTouchTreadTalkInfoRpc(Long talkId) {
                return 0;
            }

            @Override
            public int postAddTalkInfoForEntryInfoRpc(Long userId, Long talkId, Long entryId) {
                return 0;
            }

            @Override
            public int postAddTalkComment1InfoForEntryInfoRpc(Long userId, Long talkComment1Id, Long entryId) {
                return 0;
            }

            @Override
            public List<Long> getSelectTalkInfoForEntryInfoRpc(Long talkId) {
                return null;
            }

            @Override
            public List<Long> getSelectTalkComment1InfoForEntryInfoRpc(Long talkComment1Id) {
                return null;
            }

            @Override
            public int postIsExistForTalkInfoRpc(Long talkId) {
                return 0;
            }

            @Override
            public int postIsExistForTalkComment1InfoRpc(Long talkComment1Id) {
                return 0;
            }

            @Override
            public List<CommentModel> getSelectRandomTalkInfoRpc(Long topicId, Integer randomNumber) {
                return null;
            }

            @Override
            public List<CommentModel> getSelectRandomTalkComment1InfoRpc(Long talkId, Integer randomNumber) {
                return null;
            }

            @Override
            public CommentModel postSelectSingleComment1InfoRpc(Long comment1Id) {
                return null;
            }

            @Override
            public CommentModel postSelectSingleTalkInfoRpc(Long talkId) {
                return null;
            }

            @Override
            public CommentModel postSelectSingleTalkComment1InfoRpc(Long talkComment1Id) {
                return null;
            }

            @Override
            public int postUpdateTalkInfoTouchStarAmountRpc(Long talkId) {
                return 0;
            }

            @Override
            public int postUpdateTalkInfoTouchTreadAmountRpc(Long talkId) {
                return 0;
            }

            @Override
            public int postUpdateTalkComment1InfoTouchStarAmountRpc(Long talkComment1Id) {
                return 0;
            }

            @Override
            public int postUpdateTalkComment1InfoTouchTreadAmountRpc(Long talkComment1Id) {
                return 0;
            }

            @Override
            public List<CommentModel> getSelectMultiUserPublicedCommentInfoRpc(Long userId, Long page) {
                return null;
            }

            @Override
            public Long getSelectMultiUserPublicedCommentInfoPageRpc(Long userId) {
                return null;
            }

            @Override
            public Long getSelectComment1InfoStarAmountRpc(Long comment1Id) {
                return null;
            }

            @Override
            public Long getSelectTalkComment1InfoStarAmountRpc(Long talkComment1Id) {
                return null;
            }

            @Override
            public List<CommentModel> postSelectMultiLastAllCommentInfoRpc(Long topicId, Integer number) {
                return null;
            }

            @Override
            public Long postSelectComment2InfoAmountByComment1IdRpc(Long comment1Id) {
                return null;
            }

            @Override
            public Long postSelectCommentInfoAmountByTopicIdRpc(Long topicId) {
                return null;
            }

            @Override
            public Long postSelectComment1InfoAmountByTopicIdRpc(Long topicId) {
                return null;
            }

            @Override
            public Long getSelectSingleTalkForTalkCommentAmountRpc(Long topicId, Long talkId) {
                return null;
            }

            @Override
            public Long getSelectSingleTalkForTalkStarAmountRpc(Long talkId) {
                return null;
            }

            @Override
            public Long getSelectTalkCommentNumberByTopicIdRpc(Long topicId) {
                return null;
            }

            @Override
            public Long getSelectAllTalkCommentAmountByTopicIdRpc(Long topicId) {
                return null;
            }

            @Override
            public Long getSelectTalkComment1InfoAmountByTalkIdRpc(Long talkId) {
                return null;
            }

            @Override
            public Long getSelectTalkComment2InfoAmountByTalkComment1IdRpc(Long talkComment1Id) {
                return null;
            }

            @Override
            public Long getSelectAllCommentAmountRpc(Long topicId) {
                return null;
            }
        };
    }
}
