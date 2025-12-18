// ISurfaceService.aidl
package com.example.androidlearn;

// Declare any non-default types here with import statements

interface ISurfaceService {
// in（输入参数）- 默认值 | 客户端-》服务端
// out输出参数 | 服务端-》客户端
// inout 输入输出参数  | 双向传输
 void setSurface(in Surface surface);
}