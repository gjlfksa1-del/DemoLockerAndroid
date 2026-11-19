cmake_minimum_required(VERSION 3.22.1)

project(demolocker LANGUAGES CXX)

add_library(
    demolocker
    SHARED
    native-lib.cpp
)

target_compile_features(
    demolocker PRIVATE cxx_std_17
)

find_library(
    log-lib
    log
)

target_link_libraries(
    demolocker
    ${log-lib}
)
