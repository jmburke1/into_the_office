#!/bin/bash
clear
echo "Initializing environment... please wait a moment."

# 1. Install Java 21 and Git as root (this requires root privileges)
apt-get update -y && apt-get install -y openjdk-21-jdk git

# 2. Create a clean, unprivileged system user without a password
useradd -m -s /bin/bash player

# 3. Clone your repository into the new user's home directory
cd /home/player
git clone https://github.com/jmburke1/into_the_office app
cd app

# 4. Set appropriate permissions so the 'player' user owns the files
chmod +x gradlew
chown -R player:player /home/player

# 5. Build and execute the project safely strictly AS the 'player' user
su - player -c "cd /home/player/app && ./gradlew shadowJar --no-daemon"

# 6. Drop the terminal shell loop out of root and execute your TUI script as the user
clear
su - player -c "cd /home/player/app && ./just_run_it.sh"
