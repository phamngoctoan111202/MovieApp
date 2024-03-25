plugins {
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization")
}

kotlin {
    jvmToolchain(17)
}

//Bộ cài đặt này trong file Gradle dành cho Kotlin có tác dụng xác định phiên bản JDK (Java Development Kit) mà dự án sử dụng.
// Trong trường hợp này, nó đang thiết lập JVM Toolchain để sử dụng JDK 17.
// Điều này đảm bảo rằng mã nguồn Kotlin của bạn sẽ được biên dịch và chạy trên JDK 17.


//Nếu không có khối cấu hình kotlin { jvmToolchain(17) }, mã nguồn Kotlin vẫn có thể được biên dịch và chạy trên JDK mặc định của hệ thống.
// Tuy nhiên, việc cụ thể đặt JDK version sẽ phụ thuộc vào JDK mà bạn đã cài đặt trên máy tính của mình.
//Việc xác định phiên bản JDK có thể là quan trọng đối với một số dự án, đặc biệt là khi có sự khác biệt giữa các phiên bản JDK về tính năng và hiệu suất.
// Một số tính năng của Kotlin có thể tận dụng được từ các phiên bản JDK mới hơn.
//Do đó, việc sử dụng khối cấu hình kotlin { jvmToolchain(17) } giúp đảm bảo rằng dự án của bạn sẽ sử dụng JDK 17
// một cách rõ ràng và có thể tận dụng được các tính năng mới và cải tiến trong JDK này.
