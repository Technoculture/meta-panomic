# Remove the getty@.service symlink from getty.target.wants
do_install:append() {
    rm -rf ${D}${systemd_system_unitdir}/getty.target.wants/getty@.service
}
