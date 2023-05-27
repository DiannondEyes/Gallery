package com.malw.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import com.squareup.picasso.Picasso;
import java.io.File;

public class FullscreenFragment extends Fragment {
    private static final String ARG_IMAGE_PATH = "image_path";

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
            Picasso.get().load(new File(imagePath)).fit().centerInside().into((ImageView) view.findViewById(R.id.image_view));
        }
        return view;
    }
}
