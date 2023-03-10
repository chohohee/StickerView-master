package com.xiaopo.flying.stickerview;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.xiaopo.flying.sticker.StickerView;
import com.xiaopo.flying.sticker.sticker.DrawableSticker;
import com.xiaopo.flying.sticker.sticker.Sticker;
import com.xiaopo.flying.sticker.sticker.TextSticker;
import com.xiaopo.flying.stickerview.util.FileUtil;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int PERM_RQST_CODE = 110;
    private StickerView stickerView;
    private TextSticker sticker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stickerView = (StickerView) findViewById(R.id.sticker_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        stickerView.setBackgroundColor(Color.WHITE);
        stickerView.setLocked(false);

        sticker = new TextSticker(this, ContextCompat.getDrawable(this, R.drawable.sticker_transparent_background));
        sticker.setDrawable(ContextCompat.getDrawable(getApplicationContext(),
                R.drawable.sticker_transparent_background));
        sticker.setText("안녕하세요 안녕하세요 안녕하세요 안녕하세요 \uD83D\uDE35 \uD83D\uDD25 \uD83D\uDC4F \uD83D\uDCAA 안녕하세요 안녕하세요 안녕하세요 안녕하세요 \uD83D\uDE35 \uD83D\uDD25 \uD83D\uDC4F \uD83D\uDCAA");
        sticker.setTextColor(Color.WHITE);
        sticker.setTextAlign(Layout.Alignment.ALIGN_CENTER);
        sticker.resizeText();

        stickerView.setOnStickerOperationListener(new StickerView.OnStickerOperationListener() {
            @Override
            public void onStickerAdded(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerAdded");
            }

            @Override
            public void onStickerClicked(@NonNull Sticker sticker) {
                if (sticker instanceof TextSticker) {
                    ((TextSticker) sticker).setTextColor(Color.RED);
                    stickerView.replace(sticker);
                    stickerView.invalidate();
                }
                Log.d(TAG, "onStickerClicked");
            }

            @Override
            public void onStickerDeleted(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerDeleted");
            }

            @Override
            public void onStickerDragFinished(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerDragFinished");
            }

            @Override
            public void onStickerTouchedDown(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerTouchedDown");
            }

            @Override
            public void onStickerZoomFinished(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerZoomFinished");
            }
        });

        if (toolbar != null) {
            toolbar.setTitle(R.string.app_name);
            toolbar.inflateMenu(R.menu.menu_save);
            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (item.getItemId() == R.id.item_save) {
                        File file = FileUtil.getNewFile(MainActivity.this, "Sticker");
                        if (file != null) {
                            stickerView.save(file);
                            Toast.makeText(MainActivity.this, "saved in " + file.getAbsolutePath(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "the file is null", Toast.LENGTH_SHORT).show();
                        }
                    }
                    return false;
                }
            });
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERM_RQST_CODE);
        } else {
            loadSticker();
        }
    }

    private void loadSticker() {
//        Drawable drawable =
//                ContextCompat.getDrawable(this, R.drawable.iu_circle);
//        Drawable drawable1 =
//                ContextCompat.getDrawable(this, R.drawable.iu_happy);
//        stickerView.addSticker(new DrawableSticker(drawable));
//        stickerView.addSticker(new DrawableSticker(drawable1), Sticker.Position.BOTTOM | Sticker.Position.RIGHT);

        Drawable drawable3 = ContextCompat.getDrawable(this, R.drawable.iu_5760);
        stickerView.addSticker(new DrawableSticker(drawable3));

        Drawable bubble = ContextCompat.getDrawable(this, R.drawable.bubble);
        stickerView.addSticker(
                new TextSticker(getApplicationContext(), ContextCompat.getDrawable(this, R.drawable.sticker_transparent_background))
                        .setText("\uD83D\uDC98")
                        .setMaxTextSize(20)
                        .resizeText());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERM_RQST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            loadSticker();
        }
    }

    public void testReplace(View view) {
        if (stickerView.replace(sticker)) {
            Toast.makeText(MainActivity.this, "Replace Sticker successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Replace Sticker failed!", Toast.LENGTH_SHORT).show();
        }
    }

    public void testLock(View view) {
        stickerView.setLocked(!stickerView.isLocked());
    }

    public void testRemove(View view) {
        if (stickerView.removeCurrentSticker()) {
            Toast.makeText(MainActivity.this, "Remove current Sticker successfully!", Toast.LENGTH_SHORT)
                    .show();
        } else {
            Toast.makeText(MainActivity.this, "Remove current Sticker failed!", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    /**
     * todo
     * get set 테스트
     * 스티커 보존 가능할지
     *
     */
    private Sticker tmpSticker = null;

    public void getSticker(View view) {
        if (tmpSticker != null) {
            Matrix matrix = tmpSticker.getMatrix();
            float scale = tmpSticker.getCurrentScale();
            float matrixScale = tmpSticker.getMatrixScale(matrix);
            float angle = tmpSticker.getCurrentAngle();
            int a = 2;
            int b = a;
        }

        tmpSticker = stickerView.getSticker();
    }

    public void setSticker(View view) {
        stickerView.setSticker(tmpSticker);
    }

    public void testRemoveAll(View view) {
        stickerView.removeAllStickers();
    }

    public void reset(View view) {
        stickerView.removeAllStickers();
        loadSticker();
    }

    public void testAdd(View view) {
        final TextSticker sticker = new TextSticker(this, ContextCompat.getDrawable(this, R.drawable.sticker_transparent_background));
        sticker.setText("Hello, world!");
        sticker.setTextColor(Color.BLUE);
        sticker.setTextAlign(Layout.Alignment.ALIGN_CENTER);
        sticker.resizeText();

        stickerView.addSticker(sticker);
    }
}
