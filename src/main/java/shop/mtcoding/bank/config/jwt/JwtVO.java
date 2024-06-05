package shop.mtcoding.bank.config.jwt;

public interface JwtVO {
    String SECRET = "2f7a1a2180759cf89dc95c298899848264dffc49f6e35c63010163cf858b7b6a"; // 실제 운영에서는 따로 관리
    int EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER = "Authorization";
}
