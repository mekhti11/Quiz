package com.mekhti.quiz.Entity;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

@SuppressLint("ParcelCreator")
public class Question implements Parcelable{

    private String text;
    private List<String> choices ;
    private String answer;

    public Question(String text, List<String> choices, String answer) {
        this.text = text;
        this.choices = choices;
        this.answer = answer;
    }

    public Question() {
    }

    protected Question(Parcel in) {
        text = in.readString();
        choices = in.createStringArrayList();
        answer = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeStringList(choices);
        dest.writeString(answer);
    }
}
