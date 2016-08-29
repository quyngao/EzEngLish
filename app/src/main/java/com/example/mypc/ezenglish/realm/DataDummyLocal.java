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
        l.setIsrealy(0);
        l.setTime(0);
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
        l.setIsrealy(0);
        l.setTime(0);
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
        l.setIsrealy(0);
        l.setTime(0);
        l.setVocas(getlistvocar(3 * 10));
        l.setAll(getlistmp33(3 * 10));
        return l;
    }

    public Lesson getLesson4() {
        Lesson l = new Lesson();
        l.setId(4);
        l.setName("Changed");
        l.setDoc(getDoc(4));
        l.setContext("Second for beginner");
        l.setImg("/original/4/avatar.png");
        l.setIsrealy(0);
        l.setTime(0);
        l.setVocas(getlistvocar(4 * 10));
        l.setAll(getlistmp34(4 * 10));
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
        m.setLocation("/original/1/Day of the Dead Audio.mp3");
        m.setType(0);
        list.add(m);
        i++;
        m = new MP3();
        m.setId(i);
        m.setName("Day of the Dead Vocab");
        m.setContext("Voca");
        m.setLocation("/original/1/Day of the Dead Vocab.mp3");
        m.setType(1);
        list.add(m);
        i++;
        m = new MP3();
        m.setId(i);
        m.setName("Day of the Dead MS");
        m.setContext("Ministore");
        m.setLocation("/original/1/Day of the Dead MS.mp3");
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

    public RealmList<Voca> getlistvocar(int i) {
        RealmList<Voca> list = new RealmList<>();
        Voca v1 = new Voca();
        v1.setId(i);
        v1.setDescription("Dễ dàng");
        v1.setImg("/original/1/1.jpg");
        v1.setName("Easy");
        v1.setTrans("ēzē");
        i++;
        list.add(v1);
        v1 = new Voca();
        v1.setId(i);
        v1.setDescription("Tiếng anh");
        v1.setImg("/original/1/2.png");
        v1.setName("English");
        v1.setTrans("english");
        i++;
        list.add(v1);

        v1 = new Voca();
        v1.setId(i);
        v1.setDescription("HandSome");
        v1.setImg("/original/1/3.jpg");
        v1.setName("HandSome");
        v1.setTrans("ˈhansəm");
        i++;
        list.add(v1);

        v1 = new Voca();
        v1.setId(i);
        v1.setDescription("a");
        v1.setImg("/original/1/1.jpg");
        v1.setName("tu d:");
        v1.setTrans("transliteration");
        list.add(v1);
        i++;

        v1 = new Voca();
        v1.setId(i);
        v1.setDescription("HandSome");
        v1.setImg("/original/1/3.jpg");
        v1.setName("HandSome");
        v1.setTrans("ˈhansəm");
        i++;
        list.add(v1);
        return list;


    }
}
