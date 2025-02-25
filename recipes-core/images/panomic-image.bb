DESCRIPTION = "Fairly minimal image for microfabricator app (microfabricator)"
LICENSE = "MIT"

inherit core-image

DEPENDS += "rpi-bootfiles"

IMAGE_FEATURES += "\
    splash \
    package-management \
"
CORE_IMAGE_EXTRA_INSTALL = " \
        mesa \
        libdrm \
        wayland \
        wayland-protocols \
        openssh \
        openvpn \
        wayland-utils \
        weston \
        weston-init \
        libgles2 \
        libegl \
        libinput \
        json-c \
"
IMAGE_INSTALL = " \
        ffmpeg \
        glib-2.0 \
        gobject-introspection \
        nss \
        nspr \
        dbus \
        atk \
        at-spi2-atk \
        cups \
        gdk-pixbuf \
        gtk+3 \
        pango \
        cairo \
        libx11 \
        libxcomposite \
        libxdamage \
        libxext \
        libxfixes \
        libxrandr \
        fontconfig \
        libdrm \
        expat \
        libxcb \
        libxkbcommon \
        udev \
        alsa-lib \
        at-spi2-core \
        glibc \
        libgcc \
        libnss-nis \
        freerdp \
        gtk+3 \
        xwayland \
        libgbm \
        zstd \
        json-glib \
        nodejs \
        ${CORE_IMAGE_EXTRA_INSTALL} \
"

# Add an extra 1 GB of free space to the root filesystem
IMAGE_ROOTFS_EXTRA_SPACE = "10485760"

