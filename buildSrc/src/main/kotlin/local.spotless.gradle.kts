plugins {
    id("com.diffplug.spotless")
}

spotless {
//    spotless { ... }: Đây là khối cấu hình chính cho Spotless.
    kotlin {
//        kotlin { ... }: Đây là cấu hình cho việc định dạng mã nguồn Kotlin.
        target("**/*.kt", "**/*.kts")
//        target("**/*.kt", "**/*.kts"): Chỉ định các tệp cần áp dụng quy tắc định dạng Kotlin,
//        trong trường hợp này là tất cả các tệp có phần mở rộng là .kt và .kts.
        targetExclude("**/buildSrc/build/**/*.*")
//         Loại bỏ các tệp trong thư mục buildSrc/build khỏi quá trình định dạng.
        ktlint()

        indentWithSpaces()
//        indentWithSpaces(): Sử dụng khoảng trắng để thụt đầu dòng trong quá trình định dạng.
        endWithNewline()
    }

    // Don't add spotless as dependency for the Gradle's check task to facilitate separated codebase checks
    // Chỉ sửa lỗi mà không báo lỗi
    isEnforceCheck = false
}
