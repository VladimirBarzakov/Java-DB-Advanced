package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OnlineRadioDatabase {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        long playlistLength=0L;
        String line ="";
        String[] tokens = new String[3];
        int counter=0;
        for (int i = 0; i < n; i++) {

            line=reader.readLine();
            if (line.isEmpty() || !line.contains(";") || line.split(";").length!=3){
                System.out.println("Invalid song.");
                continue;
            }
            tokens = line.split(";");
            String nameArtist = tokens[0];
            String nameSong = tokens[1];
            String lengh=tokens[2];
            if (nameArtist.length()<3 || nameArtist.length()>20){
                System.out.println("Artist name should be between 3 and 20 symbols.");
                continue;
            }
            if (nameSong.length()<3 || nameSong.length()>20){
                System.out.println("Song name should be between 3 and 30 symbols.");
                continue;
            }
            if (lengh.split(":").length==2){
                tokens = lengh.split(":");
                int minutes = Integer.parseInt(tokens[0]);
                int seconds = Integer.parseInt(tokens[1]);
                if (minutes<0 || minutes>14){
                    System.out.println("Song minutes should be between 0 and 14.");
                    continue;
                }
                if ( seconds<0 || seconds>59 ){
                    System.out.println("Song seconds should be between 0 and 59.");
                    continue;
                }

                playlistLength+=minutes*60+seconds;
                counter++;
                System.out.println("Song added.");
            } else{
                System.out.println("Invalid song length.");
            }

        }
        long hours = playlistLength/3600;
        playlistLength%=3600;
        long minutes = playlistLength/60;
        long seconds = playlistLength%60;
        System.out.printf("Songs added: %d"+System.lineSeparator(),counter);
        System.out.printf("Playlist length: %dh %dm %ds",hours,minutes,seconds);
    }


}


