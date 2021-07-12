object Versions {
    const val constraintLayout = "2.0.4"
    const val coreKTX = "1.6.0"
    const val appcompat = "1.3.0"
    const val kotlinLib = "1.5.10"
    const val testJunit = "4.12"
    const val androidTestJunit = "1.4.0"
    const val androidtestEspresso = "3.4.0"
}

object LibDependency {
     val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
     val coreKTX = "androidx.core:core-ktx:${Versions.coreKTX}"
     val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
     val kotlinLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinLib}"
     val testJunit = "junit:junit:${Versions.testJunit}"
     val androidTestJunit = "androidx.test:runner:${Versions.androidTestJunit}"
     val androidtestEspresso = "androidx.test.espresso:espresso-core:${Versions.androidtestEspresso}"
}