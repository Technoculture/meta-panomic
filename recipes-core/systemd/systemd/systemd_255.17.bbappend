# systemd_%.bbappend

# Add a post-installation script
SRC_URI += "file://disable-getty-tty1.sh"

do_install:append() {
    # Install the script
    install -d ${D}${sysconfdir}/postinst.d
    install -m 0755 ${WORKDIR}/disable-getty-tty1.sh ${D}${sysconfdir}/postinst.d/disable-getty-tty1.sh
}
