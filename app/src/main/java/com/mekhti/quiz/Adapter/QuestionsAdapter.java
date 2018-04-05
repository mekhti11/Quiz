package com.mekhti.quiz.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mekhti.quiz.Entity.Question;
import com.mekhti.quiz.Listener.Listener;
import com.mekhti.quiz.R;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder>{

    private List<Question> qList;
    private Listener listener;

    public QuestionsAdapter(List<Question> qList, Listener listener) {
        this.qList = qList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_questions,parent,false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {

        holder.soru_text.setText(qList.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return qList.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView soru_text;
        public QuestionViewHolder(final View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.questions_cv);
            soru_text = itemView.findViewById(R.id.question_text);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(itemView , getAdapterPosition());
                }
            });
        }
    }
}
