FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI:append = " file://no-splash.cfg file://splash.bmp"

UBOOT_CONFIG_FRAGMENTS:append = " file://no-splash.cfg"
do_install:append() {
    install -m 0644 ${WORKDIR}/splash.bmp ${D}/boot/splash.bmp
}

# Install to boot partition
do_deploy:append() {
    install -m 0644 ${WORKDIR}/splash.bmp ${DEPLOYDIR}/splash.bmp
}
