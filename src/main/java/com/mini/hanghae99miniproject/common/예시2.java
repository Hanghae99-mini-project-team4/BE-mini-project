//package com.mini.hanghae99miniproject.common;
//
//public class 예시2 {


//
////    @Service
////    @RequiredArgsConstructor
////    public class CommentService {
////
////        private  final CommentMapper commentMapper;
////        private final PostingRepository postingRepository;
////        private final CommentRepository commentRepository;
////        private final UserRepository userRepository;
////
////        @Transactional
////        public ResponseComment createComment(String username, Long postId, RequestComment requestComment){
////            Optional<User> user = userRepository.findByUsername(username);
////
////            Posting posting = postingRepository.findById(postId)
////                    .orElseThrow(() -> new NullPointerException(NO_EXIST_POSTING_EXCEPTION_MSG.getMsg()));
////
////            Comment newComment = commentMapper.toDepthZeroComment(user.get(), posting, requestComment);
////            commentRepository.save(newComment);
////
////            return commentMapper.toResponse(newComment);
////
////        }
////
////        @Transactional
////        public ResponseComment updateComment(String username, Long commentId, RequestComment requestComment){
////
////            Comment commentFind = commentRepository.findById(commentId)
////                    .orElseThrow(() -> new IllegalArgumentException(NO_EXIST_COMMENT_EXCEPTION_MSG.getMsg()));
////
////            if(!commentFind.getUser().getUsername().equals(username)){
////                throw new IllegalArgumentException(USER_NOT_MATCH_ERROR_MSG.getMsg());
////            }
////
////            String updateComment = requestComment.getContent();
////            commentFind.updateContent(updateComment);
////
////            return commentMapper.toResponse(commentFind);
////        }
////
////        @Transactional
////        public void deleteComment(String username, Long commentId){
////
////            Comment commentFind = commentRepository.findById(commentId)
////                    .orElseThrow(() -> new IllegalArgumentException(NO_EXIST_COMMENT_EXCEPTION_MSG.getMsg()));
////
////            if(!commentFind.getUser().getUsername().equals(username)){
////                throw new IllegalArgumentException(USER_NOT_MATCH_ERROR_MSG.getMsg());
////            }
////
////            if(commentFind.getCommentGroup() == null) {
////                commentRepository.deleteByCommentGroup(commentId);
////            }
////            commentRepository.deleteById(commentId);
////        }
////
////        // 대댓글 생성
////        @Transactional
////        public ResponseComment createNestedComment(String username, Long postId, Long commentId, RequestComment requestComment){
////            Optional<User> user = userRepository.findByUsername(username);
////
////            commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException(NO_EXIST_COMMENT_EXCEPTION_MSG.getMsg()));
////            Posting posting = postingRepository.findById(postId)
////                    .orElseThrow(() -> new NullPointerException(NO_EXIST_POSTING_EXCEPTION_MSG.getMsg()));
////
////            Comment newNestedComment;
////            Optional<Integer> maxCommentDepth = commentRepository.findWithComment(commentId);
////
////            if(maxCommentDepth.isEmpty()){
////                newNestedComment = commentMapper.toNestedComment(user.get(), posting, requestComment, commentId, 1);
////            }else{
////                newNestedComment = commentMapper.toNestedComment(user.get(), posting, requestComment, commentId, maxCommentDepth.get() + 1);
////            }
////
////            commentRepository.save(newNestedComment);
////
////            return commentMapper.toResponse(newNestedComment);
////        }
////
////
////    }
//
//}
