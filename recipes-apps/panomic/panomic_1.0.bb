SUMMARY = "This program provides a panomic instrument"
AUTHOR = "Satyam Tiwary"
HOMEPAGE = "Local Directory"

SECTION = "app"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=298accf4f218779698087fdd58f8f16c"

PR = "r23"
inherit systemd

# Add the .deb file to SRC_URI
SRC_URI = " \
    file://hmi.service \
    file://hmi-ui-forge_1.0.0_arm64.deb;unpack=false \
    file://LICENSE \
"

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN} = "hmi.service"

# Set the source directory to WORKDIR since you're not using git
S = "${WORKDIR}"

RDEPENDS:${PN} += "\
    nss \
    nspr \
    dbus \
    glib-2.0 \
    gtk+3 \
    pango \
    cairo \
    alsa-lib \
    at-spi2-core \
    atk \
    libx11 \
    libxcomposite \
    libxdamage \
    libxext \
    libxfixes \
    libxrandr \
    libxkbcommon \
    libudev \
    libdrm \
    libgbm \
    libxcb \
"
do_install:append() {
    # Create the systemd system directory
    install -d ${D}${systemd_system_unitdir}
    # Install the service file with the correct permissions (0644 for systemd service files)
    install -m 0644 ${WORKDIR}/hmi.service ${D}${systemd_system_unitdir}/hmi.service
    # Extract the .deb file to a temp location
    mkdir -p ${WORKDIR}/deb-extract
    dpkg-deb -x ${WORKDIR}/hmi-ui-forge_1.0.0_arm64.deb ${WORKDIR}/deb-extract

    # Copy ONLY the required path to the final image directory
    install -d ${D}/usr/lib/hmi-ui-forge
    cp -a ${WORKDIR}/deb-extract/usr/lib/hmi-ui-forge/* ${D}/usr/lib/hmi-ui-forge/

}

# Add the systemd service file to the package
FILES:${PN} += "${systemd_system_unitdir}/system/hmi.service"
FILES:${PN} += "/usr/lib/hmi-ui-forge"
INSANE_SKIP:${PN} += "file-rdeps"

