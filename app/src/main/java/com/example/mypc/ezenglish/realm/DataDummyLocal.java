package com.example.mypc.ezenglish.realm;

import com.example.mypc.ezenglish.model.Doc;
import com.example.mypc.ezenglish.model.History;
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

    public Lesson getLesson(int id) {
        Lesson l = new Lesson();
        l.setId(id);
        l.setName("Leason" + id);
        l.setDoc(getDoc(id));
        l.setContext("ok");
        l.setImg("/original/1/avatar.jpg");
        l.setIsrealy(1);
        l.setTime(10);
        l.setVocas(getlistvocar(id * 10));
        l.setAll(getlistmp3(id * 10));
        return l;
    }

    public Topic saveTopic() {
        Topic t = new Topic();
        t.setId(1);
        t.setImg("");
        t.setName("Original");
        t.setDescription("for Biginer");
        RealmList<Lesson> list = new RealmList<>();
        for (int i = 0; i < 3; i++) {
            Lesson tmp = getLesson(i);
            list.add(tmp);
        }
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
        d.setType("audio");
        d.setLocation("/original/1/voca.txt");
        d.setName("buca");
        return d;
    }

    public RealmList<MP3> getlistmp3(int i) {
        RealmList<MP3> list = new RealmList<>();

        MP3 m = new MP3();
        m.setId(i);
        m.setName("Day of the Dead Audio");
        m.setContext("Audio");
        m.setLocation("/original/1/Day of the Dead Audio.mp3");
        m.setType("0");
        list.add(m);
        i++;
        m = new MP3();
        m.setId(i);
        m.setName("Day of the Dead Vocab");
        m.setContext("Voca");
        m.setLocation("/original/1/Day of the Dead Vocab.mp3");
        m.setType("1");
        list.add(m);
        i++;
        m = new MP3();
        m.setId(i);
        m.setName("Day of the Dead MS");
        m.setContext("Ministore");
        m.setLocation("/original/1/Day of the Dead MS.mp3");
        m.setType("2");
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
        i++;

        v1 = new Voca();
        list.add(v1);
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
