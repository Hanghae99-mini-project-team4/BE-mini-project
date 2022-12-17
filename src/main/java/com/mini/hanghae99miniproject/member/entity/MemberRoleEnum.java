package com.mini.hanghae99miniproject.member.entity;

public enum MemberRoleEnum {
    MEMBER(Authority.MEMBER),  // 사용자 권한, MEMBER : "ROLE_MEMBER"
    ADMIN(Authority.ADMIN);  // 관리자 권한, MEMBER : "ROLE_ADMIN"


    // 스트링 처리하기 위함
    private final String authority;
    MemberRoleEnum(String authority) {
        this.authority = authority;
    }
    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String MEMBER = "ROLE_MEMBER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}
