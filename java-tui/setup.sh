#!/bin/bash

# Create the player user
useradd -m -s /bin/bash player

# Find the latest Temurin 21 JDK for Linux x64
JDK_URL=$(curl -sL \
  "https://api.github.com/repos/adoptium/temurin21-binaries/releases/latest" \
  | grep 'browser_download_url.*jdk_x64_linux_hotspot' \
  | grep -v debug \
  | head -1 \
  | grep -o '"[^"]*tar\.gz"' \
  | head -1 \
  | tr -d '"')

echo "Downloading JDK from:"
echo "$JDK_URL"

# Download and unpack the JDK directly into the player's home directory
mkdir -p /home/player/jdk
curl -sL "$JDK_URL" -o /tmp/jdk.tar.gz
tar -xzf /tmp/jdk.tar.gz -C /home/player/jdk --strip-components=1
rm /tmp/jdk.tar.gz

# Clone the application
cd /home/player
git clone https://github.com/jmburke1/into_the_office app

chmod +x /home/player/app/gradlew
chmod +x /home/player/app/just_run_it.sh
chown -R player:player /home/player

# Clear the terminal canvas and then build using our locally unpacked JDK and finally
# bring your TUI to the front of the screen
clear
echo "Launching Console Application..."

# Drop down out of root privileges and run the interactive runner script
su - player -c '
    export JAVA_HOME=/home/player/jdk
    export PATH="$JAVA_HOME/bin:$PATH"
    cd /home/player/app
    ./clean_build_and_then_run_it.sh
'