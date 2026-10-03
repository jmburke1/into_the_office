#!/bin/bash
# (setup.sh content stays exactly the same to install java & create user)
apt-get update -y && apt-get install -y openjdk-21-jdk git
useradd -m -s /bin/bash player
cd /home/player && git clone https://github.com/jmburke1/into_the_office app
chmod +x /home/player/app/gradlew
chown -R player:player /home/player
su - player -c "cd /home/player/app && ./gradlew clean shadowJar --no-daemon"
