package homemade.things.ap.it.domosecurityhome;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class home1 extends View {
    private Rect rectPiano = new Rect(390, 1190, 931, 1230);


    int width;
    float intMolt;
    int height;
    private Bitmap bitmap;
    public Canvas canvas;
    private Path path;
    private Paint mPaint;
    Context context;
    private float mx,my;
    public int intPiano = 1;

    public home1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        //path = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2f);
        mPaint.setStrokeJoin(Paint.Join.ROUND);

    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        //intMolt = 1.55f;
        Log.i("canvas", "valoreintM:"+intMolt);

        if (intPiano == 1) {
            path = new Path();
            //canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            path.moveTo(256*intMolt,33*intMolt);
            path.lineTo(426*intMolt,60*intMolt);
            path.lineTo(384*intMolt,134*intMolt);
            path.lineTo(398*intMolt,135*intMolt);
            path.lineTo(440*intMolt,61*intMolt);

            path.lineTo(539*intMolt,79*intMolt);
            path.lineTo(511*intMolt,160*intMolt);
            path.lineTo(658*intMolt,188*intMolt);
            path.lineTo(632*intMolt,442*intMolt);
            path.moveTo(38*intMolt,261*intMolt);
            path.lineTo(256*intMolt,33*intMolt);

            path.lineTo(272*intMolt,145*intMolt);
            path.lineTo(385*intMolt,167*intMolt);

            path.moveTo(384*intMolt,134*intMolt);
            path.lineTo(385*intMolt,256*intMolt);
            path.lineTo(396*intMolt,258*intMolt);
            path.lineTo(433*intMolt,177*intMolt);
            path.lineTo(507*intMolt,192*intMolt);

            path.moveTo(440*intMolt,61*intMolt);
            path.lineTo(433*intMolt,177*intMolt);

            path.moveTo(398*intMolt,135*intMolt);
            path.lineTo(396*intMolt,258*intMolt);

            path.moveTo(511*intMolt,160*intMolt);
            path.lineTo(494*intMolt,280*intMolt);
            path.lineTo(619*intMolt,309*intMolt);
            path.lineTo(586*intMolt,533*intMolt);
            path.lineTo(632*intMolt,442*intMolt);

            path.moveTo(658*intMolt,188*intMolt);
            path.lineTo(619*intMolt,309*intMolt);

            path.moveTo(586*intMolt,533*intMolt);
            path.lineTo(94*intMolt,385*intMolt);
            path.lineTo(38*intMolt,261*intMolt);
            path.lineTo(94*intMolt,385*intMolt);
            path.lineTo(272*intMolt,145*intMolt);

            canvas.drawPath(path,mPaint);



            Paint paint1 = new Paint();
            paint1.setColor(context.getColor(android.R.color.holo_blue_light));
            Path path1 = new Path();
            path1.moveTo(457*intMolt,101*intMolt);
            path1.lineTo(454*intMolt,145*intMolt);
            path1.lineTo(505*intMolt,156*intMolt);
            path1.lineTo(510*intMolt,111*intMolt);
            path1.lineTo(457*intMolt,101*intMolt);
            path1.close();
            canvas.drawPath(path1, paint1);

            Paint paint2 = new Paint();
            paint2.setColor(context.getColor(android.R.color.holo_blue_light));
            Path path2 = new Path();
            path2.moveTo(218*intMolt,115*intMolt);
            path2.lineTo(244*intMolt,86*intMolt);
            path2.lineTo(257*intMolt,166*intMolt);
            path2.lineTo(235*intMolt,197*intMolt);
            path2.lineTo(218*intMolt,115*intMolt);
            path2.close();
            canvas.drawPath(path2, paint2);

            Paint paint3 = new Paint();
            paint3.setColor(context.getColor(android.R.color.holo_blue_light));
            Path path3 = new Path();
            path3.moveTo(101*intMolt,250*intMolt);
            path3.lineTo(139*intMolt,207*intMolt);
            path3.lineTo(166*intMolt,289*intMolt);
            path3.lineTo(133*intMolt,332*intMolt);
            path3.lineTo(101*intMolt,250*intMolt);
            path3.close();
            canvas.drawPath(path3, paint3);

            Drawable d = getResources().getDrawable(R.drawable.lamp, null);
            d.setBounds(50, 80, 130, 130);
            d.draw(canvas);
        }
        if (intPiano == 2) {

            path = new Path();
            //canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            path.moveTo(63*intMolt,318*intMolt);
            path.lineTo(147*intMolt,83*intMolt);
            path.lineTo(670*intMolt,41*intMolt);
            path.lineTo(923*intMolt,200*intMolt);
            path.lineTo(867*intMolt,368*intMolt);
            path.lineTo(128*intMolt,501*intMolt);

            path.lineTo(179*intMolt,215*intMolt);
            path.lineTo(147*intMolt,83*intMolt);

            path.moveTo(63*intMolt,318*intMolt);
            path.lineTo(128*intMolt,501*intMolt);


            path.moveTo(179*intMolt,215*intMolt);
            path.lineTo(328*intMolt,198*intMolt);
            path.moveTo(350*intMolt,322*intMolt);
            path.lineTo(368*intMolt,322*intMolt);
            path.lineTo(351*intMolt,164*intMolt);
            path.lineTo(327*intMolt,69*intMolt);
            path.lineTo(314*intMolt,70*intMolt);
            path.lineTo(333*intMolt,166*intMolt);
            path.lineTo(350*intMolt,322*intMolt);
            path.lineTo(328*intMolt,198*intMolt);
            path.lineTo(314*intMolt,70*intMolt);
            path.moveTo(333*intMolt,166*intMolt);
            path.lineTo(351*intMolt,164*intMolt);


            path.moveTo(440*intMolt,59*intMolt);
            path.lineTo(443*intMolt,187*intMolt);
            path.lineTo(497*intMolt,300*intMolt);
            path.lineTo(511*intMolt,301*intMolt);
            path.lineTo(515*intMolt,149*intMolt);
            path.lineTo(456*intMolt,59*intMolt);
            path.moveTo(502*intMolt,148*intMolt);
            path.lineTo(515*intMolt,149*intMolt);

            path.moveTo(807*intMolt,129*intMolt);
            path.lineTo(734*intMolt,138*intMolt);

            path.moveTo(497*intMolt,300*intMolt);
            path.lineTo(502*intMolt,148*intMolt);
            path.lineTo(440*intMolt,59*intMolt);

            path.moveTo(354*intMolt,197*intMolt);
            path.lineTo(443*intMolt,187*intMolt);

            path.moveTo(516*intMolt,179*intMolt);
            path.lineTo(650*intMolt,163*intMolt);
            path.lineTo(670*intMolt,41*intMolt);

            path.moveTo(650*intMolt,163*intMolt);
            path.lineTo(716*intMolt,227*intMolt);

            path.moveTo(807*intMolt,129*intMolt);
            path.lineTo(734*intMolt,138*intMolt);
            path.lineTo(703*intMolt,289*intMolt);
            path.lineTo(711*intMolt,296*intMolt);
            path.lineTo(739*intMolt,145*intMolt);
            path.lineTo(816*intMolt,136*intMolt);

            path.moveTo(816*intMolt,136*intMolt);
            path.lineTo(781*intMolt,286*intMolt);
            path.lineTo(711*intMolt,296*intMolt);
            path.moveTo(781*intMolt,286*intMolt);
            path.lineTo(867*intMolt,368*intMolt);

            canvas.drawPath(path,mPaint);

            Paint paint1 = new Paint();
            paint1.setColor(context.getColor(android.R.color.holo_blue_light));
            Path path1 = new Path();
            path1.moveTo(579*intMolt,74*intMolt);
            path1.lineTo(629*intMolt,70*intMolt);
            path1.lineTo(620*intMolt,167*intMolt);
            path1.lineTo(574*intMolt,172*intMolt);
            path1.lineTo(579*intMolt,74*intMolt);
            path1.close();
            canvas.drawPath(path1, paint1);

            Paint paint2 = new Paint();
            paint2.setColor(context.getColor(android.R.color.holo_blue_light));
            Path path2 = new Path();
            path2.moveTo(354*intMolt,94*intMolt);
            path2.lineTo(415*intMolt,88*intMolt);
            path2.lineTo(418*intMolt,191*intMolt);
            path2.lineTo(363*intMolt,196*intMolt);
            path2.lineTo(354*intMolt,94*intMolt);
            path2.close();
            canvas.drawPath(path2, paint2);

            Paint paint3 = new Paint();
            paint3.setColor(context.getColor(android.R.color.holo_blue_light));
            Path path3 = new Path();
            path3.moveTo(206*intMolt,108*intMolt);
            path3.lineTo(267*intMolt,102*intMolt);
            path3.lineTo(275*intMolt,156*intMolt);
            path3.lineTo(217*intMolt,163*intMolt);
            path3.lineTo(206*intMolt,108*intMolt);
            path3.close();
            canvas.drawPath(path3, paint3);

            Paint paint4 = new Paint();
            paint4.setColor(context.getColor(android.R.color.holo_blue_light));
            Path path4 = new Path();
            path4.moveTo(824*intMolt,181*intMolt);
            path4.lineTo(874*intMolt,215*intMolt);
            path4.lineTo(856*intMolt,281*intMolt);
            path4.lineTo(807*intMolt,241*intMolt);
            path4.lineTo(824*intMolt,181*intMolt);
            path4.close();
            canvas.drawPath(path4, paint4);


        }

    }

}
