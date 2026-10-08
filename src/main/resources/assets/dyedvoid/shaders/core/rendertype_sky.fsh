#version 330
#extension GL_ARB_separate_shader_objects : require

#include <minecraft:fog.glsl>
#include <minecraft:matrix.glsl>
#include <minecraft:globals.glsl>

uniform sampler2D Sampler0;

layout(location = 0) in vec4 texProj0;
layout(location = 1) in float sphericalVertexDistance;
layout(location = 2) in float cylindricalVertexDistance;

layout(location = 0) out vec4 fragColor;

void main() {
    vec4 color = textureProj(Sampler0, texProj0);
    fragColor = apply_fog(color, sphericalVertexDistance, cylindricalVertexDistance, FogEnvironmentalStart, FogEnvironmentalEnd, FogRenderDistanceStart, FogRenderDistanceEnd, FogColor);
}
