import com.adarshr.gradle.testlogger.theme.ThemeType

plugins {
    id("org.gradle.jvm-test-suite")
    id("com.adarshr.test-logger")
}

tasks.withType<Test> {
    useJUnitPlatform()

    // Enable parallel test execution
    systemProperties = mapOf(
        "junit.jupiter.execution.parallel.enabled" to "true",
        "junit.jupiter.execution.parallel.mode.default " to "concurrent",
    )
//    tasks.withType<Test>: Cấu hình tất cả các task kiểm thử (Test) trong dự án.
//useJUnitPlatform(): Sử dụng JUnit Platform để chạy các bài kiểm thử.
//systemProperties: Thiết lập các thuộc tính hệ thống cho bài kiểm thử,
// trong trường hợp này là kích thước thực hiện song song và chế độ thực hiện mặc định của JUnit Jupiter.
}

testlogger {
    theme = ThemeType.MOCHA
//    testlogger: Đây là cấu hình cho plugin test-logger.
//    theme = ThemeType.MOCHA: Đặt chủ đề cho báo cáo kiểm thử, ở đây là chủ đề Mocha.

//    testlogger là một plugin trong Gradle được sử dụng để tùy chỉnh định dạng và xuất báo cáo của các bài kiểm thử trong dự án.
//    Nó giúp bạn định rõ cách thông tin từ các bài kiểm thử sẽ được hiển thị và báo cáo ra sao.
}
