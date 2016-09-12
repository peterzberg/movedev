import json
import pygame
import time
import urllib2
import atexit

user = "pesche"
sound = False

print "MOVE! YOUR! ASS!!!!!!!"


def hasEnoughSteps():
    url = "http://192.168.2.1:8080/hackzuerich/health/" + user
    response = json.load(urllib2.urlopen(url))
    return response["enoughSteps"]


def playSound():
    global sound
    if not sound:
        print "You lazy bastard. MOOOOOOOOOOOVE"
        sound = True
        pygame.mixer.init()
        pygame.mixer.music.load("Benny-hill-theme.mp3")
        pygame.mixer.music.play(-1)


def stopSound():
    global sound
    if sound:
        print "Finally, you have enough steps. Stopping Benny Hill theme... :)"
        sound = False
        pygame.mixer.music.fadeout(1000)
        pygame.mixer.quit()

def goodbye():
    global sound
    print "Goodbye!"
    if sound:
        pygame.mixer.music.stop()
        pygame.mixer.quit();

atexit.register(goodbye)

while True:
    try:
        if hasEnoughSteps():
            stopSound()
        else:
            playSound()
        time.sleep(5)
    except Exception as ex:
        print "Attention: an error occured. But you have to move. I'll ignore it for you ;-)"
