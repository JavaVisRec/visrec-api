import org.junit.Test;

public class ChallengeTests {

    @Test
    public void firstTest() {
        String input = "id,First Name,Last Name,Suffix,email,Active,country,Notes\n"
                       + "1,Judith,Williamson,,jwilliamson0@sfgate.com,false,Poland,augue vel accumsan tellus\n"
                       + "2,Paul,Kelly,,pkelly1@reference.com,false,China,lacus at velit\n"
                       + "3,Lillian,Payne,,lpayne2@ftc.gov,false,China,pede libero quis orci\n"
                       + "4,Eric,Berry,,eberry3@qq.com,false,Jamaica,odio donec\n"
                       + "5,Marie,Dunn,,mdunn4@studiopress.com,false,Democratic Republic of the Congo,metus aenean fermentum donec ut\n"
                       + "6,Helen,Montgomery,Sr,hmontgomery5@fastcompany.com,true,Philippines,rutrum ac lobortis vel\n"
                       + "7,Jose,Kim,,jkim6@nhs.uk,false,Yemen,nunc viverra\n"
                       + "8,Carol,Mason,,cmason7@flickr.com,false,Thailand,tortor sollicitudin mi\n"
                       + "9,Andrew,Sanchez,,asanchez8@google.com.hk,true,Nigeria,purus phasellus in felis donec\n"
                       + "10,Brandon,Frazier,,bfrazier9@youku.com,true,,\n"
                       + "11,Maria,Simmons,,msimmonsa@seesaa.net,true,United States,scelerisque mauris sit amet eros\n"
                       + "12,Nancy,Robinson,,nrobinsonb@dmoz.org,true,Russia,vestibulum ac\n"
                       + "13,Thomas,Medina,Jr,tmedinac@oracle.com,true,,\n"
                       + "14,Craig,Carter,IV,ccarterd@reddit.com,false,Poland,justo lacinia eget\n"
                       + "15,Joan,Sanders,,jsanderse@imageshack.us,false,Indonesia,in eleifend quam a odio\n"
                       + "16,Amy,Jones,III,ajonesf@free.fr,true,China,ut massa\n"
                       + "17,Kathy,Wood,,kwoodg@angelfire.com,true,Peru,ipsum praesent blandit lacinia\n"
                       + "18,Christopher,Robertson,,crobertsonh@google.com.au,false,Panama,faucibus orci luctus et ultrices\n"
                       + "19,Cheryl,Harvey,,charveyi@indiatimes.com,false,Argentina,proin eu mi nulla ac\n"
                       + "20,Irene,Welch,,iwelchj@wordpress.org,false,Estonia,pede morbi porttitor";
        String output = Challenge.sortCsvColumns(input);
        System.out.println(output);
    }
}
