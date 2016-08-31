package com.example.mypc.ezenglish.realm;

import com.example.mypc.ezenglish.model.Doc;
import com.example.mypc.ezenglish.model.Lesson;
import com.example.mypc.ezenglish.model.MP3;
import com.example.mypc.ezenglish.model.Topic;
import com.example.mypc.ezenglish.model.Voca;

import io.realm.RealmList;

/**
 * Created by Quylt on 8/10/2016.
 */
public class DataDummyLocal {

    public DataDummyLocal() {
    }

    public Lesson getLesson1() {
        Lesson l = new Lesson();
        l.setId(1);
        l.setName("The Day of the Dead");
        l.setDoc(getDoc(1));
        l.setContext("First for beginner");
        l.setImg("/original/1/avatar.jpg");
        l.setIsrealy(1);
        l.setTime(20000);
        l.setVocas(getlistvocar(1 * 10));
        l.setAll(getlistmp31(1 * 10));
        return l;
    }

    public Lesson getLesson2() {
        Lesson l = new Lesson();
        l.setId(2);
        l.setName("Bubba's Food");
        l.setDoc(getDoc(2));
        l.setContext("Second for beginner");
        l.setImg("/original/2/avatar.jpg");
        l.setIsrealy(1);
        l.setTime(30000);
        l.setVocas(getlistvocar(2 * 10));
        l.setAll(getlistmp32(2 * 10));
        return l;
    }

    public Lesson getLesson3() {
        Lesson l = new Lesson();
        l.setId(3);
        l.setName("A Kiss");
        l.setDoc(getDoc(3));
        l.setContext("For beginner");
        l.setImg("/original/3/avatar.jpg");
        l.setIsrealy(1);
        l.setTime(40000);
        l.setVocas(getlistvocar(3 * 10));
        l.setAll(getlistmp33(3 * 10));
        return l;
    }

    public Lesson getLesson4() {
        Lesson l = new Lesson();
        l.setId(4);
        l.setName("Changed");
        l.setDoc(getDoc(4));
        l.setContext(" for beginner");
        l.setImg("/original/4/avatar.png");
        l.setIsrealy(1);
        l.setTime(50000);
        l.setVocas(getlistvocar(4 * 10));
        l.setAll(getlistmp34(4 * 10));
        return l;
    }

    public Lesson getLesson5() {
        Lesson l = new Lesson();
        l.setId(5);
        l.setName("Drag");
        l.setDoc(getDoc(5));
        l.setContext(" for beginner");
        l.setImg("/original/5/avatar.jpg");
        l.setIsrealy(2);
        l.setTime(12220);
        l.setVocas(getlistvocar(5 * 10));
        l.setAll(getlistmp35(5 * 10));
        return l;
    }

    public Lesson getLesson6() {
        Lesson l = new Lesson();
        l.setId(6);
        l.setName("Intimacy");
        l.setDoc(getDoc(6));
        l.setContext("for beginner");
        l.setImg("/original/6/avatar.jpg");
        l.setIsrealy(0);
        l.setTime(0);
        l.setVocas(getlistvocar(6 * 10));
        l.setAll(getlistmp36((6 * 10), 6));
        return l;
    }

    public Topic saveTopic() {
        Topic t = new Topic();
        t.setId(1);
        t.setImg("");
        t.setName("Original");
        t.setDescription("for Biginer");
        RealmList<Lesson> list = new RealmList<>();

        Lesson tmp = getLesson1();
        list.add(tmp);

        tmp = getLesson2();
        list.add(tmp);
        tmp = getLesson3();
        list.add(tmp);
        tmp = getLesson4();
        list.add(tmp);
        tmp = getLesson5();
        list.add(tmp);


        tmp = getLesson6();
        list.add(tmp);

        t.setLessons(list);
        return t;
    }

    public void showTopic(Topic t) {
        System.out.println("id :" + t.getId());
        System.out.println("name :" + t.getName());
        for (int i = 0; i < t.getLessons().size(); i++) {
            showLesson(t.getLessons().get(i));
        }
    }

    public void showLesson(Lesson l) {
        System.out.println("leason :" + l.getId());
        System.out.println("name :" + l.getName());
        System.out.println("img :" + l.getImg());
        System.out.println("doc :" + l.getDoc().getId() + "--" + l.getDoc().getName());
        for (int j = 0; j < l.getVocas().size(); j++) {
            System.out.println("voca :" + l.getVocas().get(j).getId() + "   " + l.getVocas().get(j).getName());
        }
        for (int j = 0; j < l.getAll().size(); j++) {
            System.out.println("mp3 :" + l.getAll().get(j).getId() + "   " + l.getAll().get(j).getName() + "  " + l.getAll().get(j).getLocation());
        }
    }

    public Doc getDoc(int i) {
        Doc d = new Doc();
        d.setId(i);
        d.setType("Audio");
        d.setLocation("/original/" + i + "/voca.txt");
        d.setName("Text");
        return d;
    }

    public RealmList<MP3> getlistmp31(int i) {
        RealmList<MP3> list = new RealmList<>();

        MP3 m = new MP3();
        m.setId(i);
        m.setName("Day of the Dead Audio");
        m.setContext("Audio");
        m.setLocation("/original/1/Day%20of%20the%20Dead%20Audio.mp3");
        m.setType(0);
        list.add(m);
        i++;
        m = new MP3();
        m.setId(i);
        m.setName("Day of the Dead Vocab");
        m.setContext("Voca");
        m.setLocation("/original/1/Day%20of%20the%20Dead%20Vocab.mp3");
        m.setType(1);
        list.add(m);
        i++;
        m = new MP3();
        m.setId(i);
        m.setName("Day of the Dead MS");
        m.setContext("Ministore");
        m.setLocation("/original/1/Day%20of%20the%20Dead%20MS.mp3");
        m.setType(2);
        list.add(m);
        i++;
        return list;
    }

    public RealmList<MP3> getlistmp32(int i) {
        RealmList<MP3> list = new RealmList<>();

        MP3 m = new MP3();
        m.setId(i);
        m.setName("Bubba's Food Audio");
        m.setContext("Audio");
        m.setLocation("/original/2/Bubba's Food Audio.mp3");
        m.setType(0);
        list.add(m);
        i++;
        m = new MP3();
        m.setId(i);
        m.setName("Bubba's Food Vocab");
        m.setContext("Voca");
        m.setLocation("/original/2/Bubba's Food Vocab.mp3");
        m.setType(1);
        list.add(m);
        i++;
        m = new MP3();
        m.setId(i);
        m.setName("Bubba's Food MS A");
        m.setContext("Ministore");
        m.setLocation("/original/2/Bubba's Food MS-A.mp3");
        m.setType(2);
        list.add(m);
        i++;

        m = new MP3();
        m.setId(i);
        m.setName("Bubba's Food MS B");
        m.setContext("Ministore");
        m.setLocation("/original/2/Bubba's Food MS-B.mp3");
        m.setType(2);
        list.add(m);
        i++;

        m = new MP3();
        m.setId(i);
        m.setName("Bubba's Food MS C");
        m.setContext("Ministore");
        m.setLocation("/original/2/Bubba's Food MS-B.mp3");
        m.setType(2);
        list.add(m);
        i++;
        return list;
    }

    public RealmList<MP3> getlistmp33(int i) {
        RealmList<MP3> list = new RealmList<>();

        MP3 m = new MP3();
        m.setId(i);
        m.setName("A Kiss Audio");
        m.setContext("Audio");
        m.setLocation("/original/3/A Kiss Audio.mp3");
        m.setType(0);
        list.add(m);
        i++;
        m = new MP3();
        m.setId(i);
        m.setName("A Kiss Vocab");
        m.setContext("Voca");
        m.setLocation("/original/3/A Kiss Vocab.mp3");
        m.setType(1);
        list.add(m);
        i++;
        m = new MP3();
        m.setId(i);
        m.setName("A Kiss MS A");
        m.setContext("Ministore");
        m.setLocation("/original/3/A Kiss MS-A.mp3");
        m.setType(2);
        list.add(m);
        i++;

        m = new MP3();
        m.setId(i);
        m.setName("A Kiss MS B");
        m.setContext("Ministore");
        m.setLocation("/original/3/A Kiss MS-B.mp3");
        m.setType(2);
        list.add(m);
        i++;


        return list;
    }

    public RealmList<MP3> getlistmp34(int i) {
        RealmList<MP3> list = new RealmList<>();

        MP3 m = new MP3();
        m.setId(i);
        m.setName("Changed Audio");
        m.setContext("Audio");
        m.setLocation("/original/4/Changed Audio.mp3");
        m.setType(0);
        list.add(m);
        i++;
        m = new MP3();
        m.setId(i);
        m.setName("Changed Vocab");
        m.setContext("Voca");
        m.setLocation("/original/4/Changed Vocab.mp3");
        m.setType(1);
        list.add(m);
        i++;
        m = new MP3();
        m.setId(i);
        m.setName("Changed MS A");
        m.setContext("Ministore");
        m.setLocation("/original/4/Changed MS-A.mp3");
        m.setType(2);
        list.add(m);
        i++;

        m = new MP3();
        m.setId(i);
        m.setName("Changed MS B");
        m.setContext("Ministore");
        m.setLocation("/original/4/Changed MS-B.mp3");
        m.setType(2);
        list.add(m);
        i++;
        return list;
    }

    public RealmList<MP3> getlistmp35(int i) {
        RealmList<MP3> list = new RealmList<>();

        MP3 m = new MP3();
        m.setId(i);
        m.setName("Drag Audio");
        m.setContext("Audio");
        m.setLocation("/original/5/Drag Audio.mp3");
        m.setType(0);
        list.add(m);
        i++;
        m = new MP3();
        m.setId(i);
        m.setName("Drag Vocab");
        m.setContext("Voca");
        m.setLocation("/original/5/Drag Vocab.mp3");
        m.setType(1);
        list.add(m);
        i++;
        m = new MP3();
        m.setId(i);
        m.setName("Drag MS-A");
        m.setContext("Ministore");
        m.setLocation("/original/5/Drag MS-A.mp3");
        m.setType(2);
        list.add(m);
        i++;

        m = new MP3();
        m.setId(i);
        m.setName("Drag MS-B");
        m.setContext("Ministore");
        m.setLocation("/original/5/Drag MS-B.mp3");
        m.setType(2);
        list.add(m);
        i++;
        return list;
    }

    public RealmList<MP3> getlistmp36(int i, int j) {
        RealmList<MP3> list = new RealmList<>();

        MP3 m = new MP3();
        m.setId(i);
        m.setName("Intimacy Audio");
        m.setContext("Audio");
        m.setLocation("/original/" + j + "/Intimacy%20Audio.mp3");
        m.setType(0);
        list.add(m);
        i++;
        m = new MP3();
        m.setId(i);
        m.setName("Intimacy Vocab");
        m.setContext("Voca");
        m.setLocation("/original/" + j + "/Intimacy%20Vocab.mp3");
        m.setType(1);
        list.add(m);
        i++;
        m = new MP3();
        m.setId(i);
        m.setName("Intimacy MS-A");
        m.setContext("Ministore");
        m.setLocation("/original/" + j + "/Intimacy%20MS-A.mp3");
        m.setType(2);
        list.add(m);
        i++;

        m = new MP3();
        m.setId(i);
        m.setName("Intimacy MS-B");
        m.setContext("Ministore");
        m.setLocation("/original/" + j + "/Intimacy%20MS-B.mp3");
        m.setType(2);
        list.add(m);
        i++;
        return list;
    }

    public RealmList<Voca> getlistvocar(int i) {
        RealmList<Voca> list = new RealmList<>();
        Voca v1 = new Voca();
        v1.setId(i);
        v1.setDescription("An area of ground in which dead bodies are buried");
        v1.setImg("/original/1/1.jpg");
        v1.setName("Cemetery");
        v1.setTrans("/ˈsɛməˌtɛri/");
        v1.setType("N");
        i++;
        list.add(v1);
        v1 = new Voca();
        v1.setId(i);
        v1.setDescription("Atmosphere");
        v1.setImg("/original/1/2.jpg");
        v1.setName("Atmosphere");
        v1.setTrans("/'ætməsfiə/");
        v1.setType("N");
        i++;
        list.add(v1);

        v1 = new Voca();
        v1.setId(i);
        v1.setDescription("A place in the ground where a dead person is buried");
        v1.setImg("/original/1/3.jpg");
        v1.setName("Grave");
        v1.setTrans("/ɡreɪv/");
        v1.setType("N");

        i++;
        list.add(v1);

        v1 = new Voca();
        v1.setId(i);
        v1.setDescription("a person in your family who lived a long time ago");
        v1.setImg("/original/1/4.jpg");
        v1.setName("Ancestor");
        v1.setTrans("/ˈæn.ses.tɚ/");
        v1.setType("N");
        list.add(v1);
        i++;

        v1 = new Voca();
        v1.setId(i);
        v1.setDescription("a toy that flies in the air while you hold it by a long string");
        v1.setImg("/original/1/5.jpg");
        v1.setName("Kite");
        v1.setTrans("/kaɪt/");
        v1.setType("N");
        i++;
        list.add(v1);


        v1 = new Voca();
        v1.setId(i);
        v1.setDescription("serious and sad");
        v1.setImg("/original/1/6.jpg");
        v1.setName("Somber");
        v1.setTrans("/ˈsɒm.bər/");
        v1.setType("adj");
        i++;
        list.add(v1);
        v1 = new Voca();
        v1.setId(i);
        v1.setDescription("a particular way of thinking about or dealing with something");
        v1.setImg("/original/1/7.jpg");
        v1.setName("Approach");
        v1.setTrans("/əˈproʊtʃ/");
        v1.setType("N");
        i++;
        list.add(v1);
        v1 = new Voca();
        v1.setId(i);
        v1.setDescription("to die");
        v1.setImg("/original/1/8.jpg");
        v1.setName("Pass away");
        v1.setTrans("/pɑːs əˈweɪ/");
        v1.setType("Phrasal verb");
        i++;
        list.add(v1);
        v1 = new Voca();
        v1.setId(i);
        v1.setDescription("a toy that flies in the air while you hold it by a long string");
        v1.setImg("/original/1/9.jpg");
        v1.setName("Find");
        v1.setTrans("/faɪnd/");
        v1.setType("V");
        i++;
        list.add(v1);
        return list;


    }
}
