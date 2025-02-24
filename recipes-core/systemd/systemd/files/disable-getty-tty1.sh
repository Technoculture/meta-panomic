#!/bin/sh

# Disable getty@tty1.service
systemctl stop getty@tty1.service
systemctl disable getty@tty1.service

