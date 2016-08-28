#ifndef _EDLIB_H_
#define _EDLIB_H_

#include "EdgeMap.h"

/// Detect Edges by Edge Drawing (ED). Steps of the algorithm:
/// (1) Smooth the image with a 5x5 Gaussian kernel with sigma=smoothingSigma
/// (2) Compute the gradient magnitude and directions using the GradientOperator (can be Prewitt, Sobel, Scharr)
/// (3) Compute the anchors using ANCHOR_THRESH
/// (4) Link the anchors using Edge Drawing's Smart Routing Algorithm to obtain edge segments
/// (5) Return the edge segments to the user
/// Note: smoothingSigma must be >= 1.0
EdgeMap *DetectEdgesByED(unsigned char *srcImg, int width, int height, GradientOperator op, int GRADIENT_THRESH, int ANCHOR_THRESH, double smoothingSigma);

/// (1) Use DetectEdgesByED(srcImg, width, height, PREWITT_OPERATOR, 16, 0, smoothingSigma) to ontain ALL edge segments in the image
/// (2) Validate the edge segments using the Helmholtz principle, returning only the validated edge segments
/// Note: smoothingSigma must be >= 1.0
EdgeMap *DetectEdgesByEDPF(unsigned char *srcImg, int width, int height, double smoothingSigma);

#endif