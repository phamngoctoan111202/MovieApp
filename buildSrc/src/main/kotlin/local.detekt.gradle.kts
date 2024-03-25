import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("io.gitlab.arturbosch.detekt")
}

repositories {
    mavenCentral()
}

val detektCheck by tasks.registering(Detekt::class) {
    description = "Checks that sourcecode satisfies detekt rules."
    autoCorrect = false
}
//: Định nghĩa một task Detekt có tên là detektCheck, dùng để kiểm tra mã nguồn theo các quy tắc Detekt.
// Tùy chỉnh autoCorrect để không thực hiện tự động sửa lỗi.
val detektApply by tasks.registering(Detekt::class) {
    description = "Applies code formatting rules to sourcecode in-place."
    autoCorrect = true
}
//: Định nghĩa một task Detekt có tên là detektApply, dùng để áp dụng các quy tắc Detekt để sửa lỗi trong mã nguồn (autoCorrect = true).

//Cấu hình cho cả hai tasks detektCheck và detektApply.
configure(listOf(detektCheck, detektApply)) {
    configure {
        group = "verification"
        parallel = true
        ignoreFailures = false
        setSource(file(rootDir))

        // Custom detekt config
        config.setFrom("$rootDir/detekt.yml")

        // Use default configuration on top of custom config
        // (new detect rules will work out of the box after upgrading detekt version)
        buildUponDefaultConfig = true

        // Runs detekt for all files in the Gradle project and all subprojects without
        // a need to configure detekt plugin in every subproject.
        include("**/*.kt", "**/*.kts")
        exclude("**/resources/**", "**/build/**", "**/generated/**")

        reports {
            html.required.set(true)
            xml.required.set(true)
        }

    //        configure(listOf(detektCheck, detektApply)) { }: Cấu hình cho cả hai tasks detektCheck và detektApply.
    //group = "verification": Gán task vào nhóm "verification" của Gradle.
    //parallel = true: Cho phép chạy các task Detekt song song để tăng hiệu suất.
    //ignoreFailures = false: Không bỏ qua lỗi, task sẽ thất bại nếu có lỗi xảy ra.
    //setSource(file(rootDir)): Thiết lập thư mục nguồn cần kiểm tra là thư mục gốc của dự án.
    //config.setFrom("$rootDir/detekt.yml"): Đọc cấu hình Detekt từ file detekt.yml tại thư mục gốc dự án.
    //buildUponDefaultConfig = true: Sử dụng cấu hình mặc định của Detekt và thêm các cấu hình tùy chỉnh từ detekt.yml lên trên.
    //include("**/*.kt", "**/*.kts"): Áp dụng Detekt cho tất cả các file có đuôi là .kt hoặc .kts.
    //exclude("**/resources/**", "**/build/**", "**/generated/**"): Loại bỏ các thư mục không muốn kiểm tra.

//        reports { html.required.set(true) xml.required.set(true) }: Thiết lập sử dụng báo cáo HTML và XML từ Detekt để xem kết quả kiểm tra chất lượng mã nguồn.

    }
}
