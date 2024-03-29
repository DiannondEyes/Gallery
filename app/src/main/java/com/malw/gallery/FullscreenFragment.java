package com.malw.gallery;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class FullscreenFragment extends Fragment {
    private static final String ARG_IMAGE_PATH = "image_path";
    private Handler handler = new Handler();

    // Создаем новый экземпляр фрагмента с указанным путем к изображению
    public static FullscreenFragment newInstance(String imagePath) {
        Bundle args = new Bundle();
        args.putString(ARG_IMAGE_PATH, imagePath);
        FullscreenFragment fragment = new FullscreenFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    // Создание и настройка визуального интерфейса фрагмента
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fullscreen, container, false);
        String imagePath = getArguments() != null ? getArguments().getString(ARG_IMAGE_PATH) : null;

        // Если указан путь к изображению, загружаем его с помощью библиотеки Picasso и отображаем в ImageView
        if (imagePath != null) {
            PhotoView imageView = view.findViewById(R.id.image_view);
            imageView.setOnClickListener(v -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    toggleDateVisibility(view);
                }
            });
            Picasso.get().load(new File(imagePath)).fit().centerInside().into(imageView);
        }
        return view;
    }

    private void toggleDateVisibility(View view) {
        TextView dateTextView = view.findViewById(R.id.date_text_view);
        Animation fadeInOut = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_out);
        if (dateTextView.getVisibility() == View.VISIBLE) {
            dateTextView.setVisibility(View.INVISIBLE);
            handler.removeCallbacksAndMessages(null);
        } else {
            Image image = getImage();
            if (image != null) {
                String date = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    date = getCreationDate(image.getPath());
                }
                dateTextView.setText(date);
                dateTextView.setVisibility(View.VISIBLE);
                dateTextView.startAnimation(fadeInOut);
                // Скрываем TextView через 5 секунд
                handler.postDelayed(() -> {
                    dateTextView.setVisibility(View.GONE);
                }, 5000);
            }
        }
    }


    private Image getImage() {
        if (getArguments() != null) {
            String imagePath = getArguments().getString(ARG_IMAGE_PATH);
            if (imagePath != null) {
                return new Image("", imagePath);
            }
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getCreationDate(String imagePath) {
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            BasicFileAttributes attributes = null;
            try {
                attributes = Files.readAttributes(imageFile.toPath(), BasicFileAttributes.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (attributes != null) {
                Instant instant = attributes.creationTime().toInstant();
                LocalDateTime ldt = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
                return ldt.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
            }
        }
        return "";
    }
}
