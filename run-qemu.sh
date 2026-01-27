#!/bin/bash
# Run QEMU with the built Yocto image
# Usage: ./run-qemu.sh [build-dir]

BUILD_DIR="${1:-firmware/meta-pwomise/build-qemu}"
IMAGE_DIR="$BUILD_DIR/tmp/deploy/images/qemux86-64"

# Set up Yocto environment if available
if [ -f "$BUILD_DIR/../../poky-scarthgap/oe-init-build-env" ]; then
    source "$BUILD_DIR/../../poky-scarthgap/oe-init-build-env" "$BUILD_DIR" > /dev/null
fi

if [ ! -d "$IMAGE_DIR" ]; then
    echo "Error: Image directory not found: $IMAGE_DIR"
    echo "Build the image first with: python pwomise.py build yocto-qemu"
    exit 1
fi

# Find the latest .wic image
WIC_IMAGE=$(ls -t "$IMAGE_DIR"/*.wic 2>/dev/null | head -1)

if [ -z "$WIC_IMAGE" ]; then
    echo "Error: No .wic image found in $IMAGE_DIR"
    exit 1
fi

echo "Starting QEMU with image: $WIC_IMAGE"
echo "Login: root (no password)"
echo "Press Ctrl-A then X to exit QEMU"
echo ""

# Run QEMU with appropriate settings
runqemu \
    qemux86-64 \
    wic \
    nographic \
    kvm \
    slirp \
    "$WIC_IMAGE"
