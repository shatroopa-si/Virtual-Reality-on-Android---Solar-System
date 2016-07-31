package com.example.android.version7;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

// StereoRenderer abstracts all stereoscopic rendering details from the renderer
public class SpaceRenderer implements GLSurfaceView.Renderer {

    /**
     * Tilt the spheres a little.
     */
    private static final int AXIAL_TILT_DEGREES = 30;

    /**
     * Clear colour, alpha component.
     */
    private static final float CLEAR_RED = 0.0f;

    /**
     * Clear colour, alpha component.
     */
    private static final float CLEAR_GREEN = 0.0f;

    /**
     * Clear colour, alpha component.
     */
    private static final float CLEAR_BLUE = 0.0f;

    /**
     * Clear colour, alpha component.
     */
    private static final float CLEAR_ALPHA = 0.5f;

    /**
     * Perspective setup, field of view component.
     */
    private static final float FIELD_OF_VIEW_Y = 45.0f;

    /**
     * Perspective setup, near component.
     */
    private static final float Z_NEAR = 0.1f;

    /**
     * Perspective setup, far component.
     */
    private static final float Z_FAR = 100.0f;

    /**
     * Object distance on the screen. move it back a bit so we can see it!
     */
    private static final float OBJECT_DISTANCE = -15.0f;


    /**
     * The context.
     */
    private final Context mContext;

    /**
     * The rotation angle, just to give the screen some action.
     */
    private float mRotationAngle;

    // Controller Object
    SpaceController controller;

    // Root of Scene Graph
    Node root;

    // Window Width and height
    public static int screenWidth;
    public static int screenHeight;

    // Toggle Mode
    public static boolean binocular = false;

    // Camera Positions
    public static float positionCamera[] = {0.0f, 0.0f, 5.0f};
    // Center of Scene
    public static float centerOfScene[] = {0.0f, 0.0f, 0.0f};

    float xMin = -10, xMax = 10;
    float yMin = -3, yMax = 3;
    static float x = 0, y = 0;
    static float theta = 0.0f;

    /**
     * Constructor to set the handed over context.
     *
     * @param context The context.
     */
    public SpaceRenderer(final Context context) {
        this.mContext = context;
        controller = new SpaceController();
        // Initialization of the Scene
        root = controller.initCreateRelate();

    }

    @Override
    public void onDrawFrame(final GL10 gl) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 00f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        if (binocular) {


                theta = (float) Math.acos(homePage.values[3]);

                    float x1, y1, newx, newy, dispx, dispy, alpha, factor = 0.5f;

                    x = (1 - factor) * (homePage.values[0] / (float) Math.sin(theta)) + factor * x;
                    y = (1 - factor) * (homePage.values[2] / (float) Math.sin(theta)) + factor * x;

                    alpha = (float) Math.atan(y / x);
                    x1 = x * (float) Math.cos(alpha);
                    y1 = y * (float) Math.sin(alpha);

                    newx = x1 / (float) Math.cos((theta * 2) + alpha);
                    newy = y1 / (float) Math.sin((theta * 2) + alpha);

                    dispx = newx - x;
                    dispy = (homePage.values[1] * 5) + 0.5f;
                    if ((centerOfScene[0] >= xMin && centerOfScene[0] <= xMax) && ((theta) >= 0.2 || (theta) <= -0.2) ){
                        centerOfScene[0] += dispx / 10;
                    }
                    else {
                        if (centerOfScene[0] >= xMax)
                            centerOfScene[0] -= 0.2f;
                        else if (centerOfScene[0] <= xMin)
                            centerOfScene[0] += 0.2f;
                    }

            gl.glViewport(0, 0, screenWidth / 2 - 1, screenHeight);
            gl.glLoadIdentity();
            GLU.gluLookAt(gl, positionCamera[0], positionCamera[1], positionCamera[2], centerOfScene[0], centerOfScene[1], centerOfScene[2], 0.0f, 1.0f, 0.0f);
            gl.glPushMatrix();
            gl.glTranslatef(2.0f, 0.0f, OBJECT_DISTANCE);
            gl.glTranslatef(0, dispy, 0);
            gl.glRotatef(AXIAL_TILT_DEGREES, 1, 0, 0);
            Node.drawSceneGraph(root, gl);
            gl.glPopMatrix();


            gl.glViewport(screenWidth / 2 + 1, 0, screenWidth / 2, screenHeight);
            gl.glLoadIdentity();
            GLU.gluLookAt(gl, positionCamera[0], positionCamera[1], positionCamera[2], centerOfScene[0], centerOfScene[1], centerOfScene[2], 0.0f, 1.0f, 0.0f);
            gl.glPushMatrix();
            gl.glTranslatef(2.0f, 0.0f, OBJECT_DISTANCE);
            gl.glTranslatef(0, dispy, 0);
            gl.glRotatef(AXIAL_TILT_DEGREES, 1, 0, 0);
            Node.drawSceneGraph(root, gl);
            gl.glPopMatrix();


        } else

        {
            positionCamera[0] = 0.0f;
            positionCamera[1] = 0.0f;
            positionCamera[2] = 5.0f;

            centerOfScene[0] = 0.0f;
            centerOfScene[1] = 0.0f;
            centerOfScene[2] = 0.0f;

            gl.glViewport(0, 0, screenWidth, screenHeight);
            gl.glLoadIdentity();
            GLU.gluLookAt(gl, positionCamera[0], positionCamera[1], positionCamera[2], centerOfScene[0], centerOfScene[1], centerOfScene[2], 0.0f, 1.0f, 0.0f);
            gl.glPushMatrix();
            gl.glTranslatef(0.0f, 0.0f, OBJECT_DISTANCE);
            gl.glRotatef(AXIAL_TILT_DEGREES, 1, 0, 0);
            Node.drawSceneGraph(root, gl);
            gl.glPopMatrix();
        }

        gl.glFlush();

    }

    @Override
    public void onSurfaceChanged(final GL10 gl, final int width, final int height) {
        screenHeight = height;
        screenWidth = width;

        final float aspectRatio = (float) width / (float) (height == 0 ? 1 : height);

        //gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();

        GLU.gluPerspective(gl, FIELD_OF_VIEW_Y, aspectRatio, Z_NEAR, Z_FAR);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

    }


    @Override
    public void onSurfaceCreated(final GL10 gl, final EGLConfig config) {

        Node.loadTextureSceneGraph(root, gl, mContext);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glClearColor(CLEAR_RED, CLEAR_GREEN, CLEAR_BLUE, CLEAR_ALPHA);
        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);

    }
}