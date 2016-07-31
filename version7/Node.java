package com.example.android.version7;

import android.content.Context;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by HP on 7/23/2016.
 */
public class Node {


    public List<Node> children = new ArrayList<Node>();
    private Node parent;
    private String info;
    private Sphere mObject;
    private int seqNo;

    private static final int DEPTH = 4;

    // Render Count
    private static int countRender = 0;
    private static float spinSphere = 0.01f;
    public static Node moon;

    public Node(Node parent, String info) {
        this.parent = parent;
        this.info = info;
        moon = new Node(null, "moon", 11);
    }

    public Node(Node parent, String info, int sequenceNumber ){
        this.parent = parent;
        this.info = info;
        this.mObject = new Sphere(sequenceNumber, DEPTH);
        this.seqNo = sequenceNumber;
    }

    public void addChild(Node element){
        this.children.add(element);
        element.parent = this;
    }

    public int getNumberOfChild()
    {
        return children.size();
    }

    public Node getChild(int i){
        return children.get(i);
    }

    public static void drawSceneGraph(Node start, GL10 gl)
    {
        if(start.children.isEmpty())
        {
            gl.glPushMatrix();
            if(start.mObject.nameOfBody.equalsIgnoreCase("Sun"))
                countRender++;
            else
                gl.glRotatef((start.mObject.speedOfRotation / 15) * countRender, 0, 1, 0);

            gl.glTranslatef(0.0f, 0.0f, -start.mObject.distanceFromParent);

            if(!(start.mObject.nameOfBody.equalsIgnoreCase("Sun")))
            {
                gl.glRotatef(spinSphere, 0, 1, 0);
                spinSphere += 0.5f;
            }

            // Draw Planet
            start.mObject.draw(gl);

            // Rings
            gl.glPushMatrix();

            if(start.mObject.hasRings)
            {
                float r = 0.7f;
                float triangle[] = {

                        0.0f, 0.0f, 0.f,

                        0.0f, 0.0f, r,
                        r*0.965f, 0.0f, r*0.258f,
                        r*0.866f, 0.0f, r*0.5f,
                        r*0.707f, 0.0f, r*0.707f,
                        r*0.5f, 0.0f, r*0.866f,
                        r*0.258f, 0.0f, r*0.965f,
                        r, 0.0f, 0.0f,
                        r*0.965f, 0.0f, -r*0.258f,
                        r*0.866f, 0.0f, -r*0.5f,
                        r*0.707f, 0.0f, -r*0.707f,
                        r*0.5f, 0.0f, -r*0.866f,
                        r*0.258f, 0.0f, -r*0.965f,
                        0.0f, 0.0f, -r,
                        -r*0.965f, 0.0f, -r*0.258f,
                        -r*0.866f, 0.0f, -r*0.5f,
                        -r*0.707f, 0.0f, -r*0.707f,
                        -r*0.5f, 0.0f, -r*0.866f,
                        -r*0.258f, 0.0f, -r*0.965f,
                        -r, 0.0f, 0.0f,
                        -r*0.965f, 0.0f, r*0.258f,
                        -r*0.866f, 0.0f, r*0.5f,
                        -r*0.707f, 0.0f, r*0.707f,
                        -r*0.5f, 0.0f, r*0.866f,
                        -r*0.258f, 0.0f, r*0.965f,
                        0.0f, 0.0f, r
                };

                ByteBuffer bb = ByteBuffer.allocateDirect(triangle.length * 4);
                bb.order(ByteOrder.nativeOrder());

                FloatBuffer vertexBuffer;
                vertexBuffer = bb.asFloatBuffer();
                vertexBuffer.put(triangle);
                vertexBuffer.position(0);

                gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
                gl.glColor4f(0.489f, 0.408f, 0.920f, 1.0f);
                gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
                gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, triangle.length / 3);
                gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
                gl.glColor4f(1.0f,1.0f,1.0f,1.0f);
            }
            gl.glPopMatrix();

            // Moons
            if(start.mObject.numberOfMoons > 0)
            {
                gl.glRotatef((start.mObject.speedOfRotation / 6) * countRender, 0, 1, 0);
                gl.glTranslatef(-0.8f, 0.0f, 0.8f);
                moon.mObject.draw(gl);

            }

            gl.glPopMatrix();
        }
        else
        {
            for(int i = 0; i < start.children.size(); i++)
            {
                drawSceneGraph(start.children.get(i), gl);
            }
        }

    }


    public static void loadTextureSceneGraph(Node start, GL10 gl, Context mContext)
    {
        if(start.children.isEmpty())
        {

            int i = start.seqNo;
            switch(i) {
                case 0:
                    start.mObject.loadGLTexture(gl, mContext, R.drawable.sun);
                    break;
                case 1:
                    start.mObject.loadGLTexture(gl, mContext, R.drawable.mercury);
                    break;
                case 2:
                    start.mObject.loadGLTexture(gl, mContext, R.drawable.venus);
                    break;
                case 3:
                    start.mObject.loadGLTexture(gl, mContext, R.drawable.earth);
                    break;
                case 4:
                    start.mObject.loadGLTexture(gl, mContext, R.drawable.mars);
                    break;
                case 5:
                    start.mObject.loadGLTexture(gl, mContext, R.drawable.jupiter);
                    break;
                case 6:
                    start.mObject.loadGLTexture(gl, mContext, R.drawable.saturn);
                    break;
                case 7:
                    start.mObject.loadGLTexture(gl, mContext, R.drawable.uranus);
                    break;
                case 8:
                    start.mObject.loadGLTexture(gl, mContext, R.drawable.neptune);
                    break;
                default:
                    start.mObject.loadGLTexture(gl, mContext, R.drawable.moon);
                    break;
            }
        }
        else
        {
            for(int i = 0; i < start.children.size(); i++)
            {
                loadTextureSceneGraph(start.children.get(i), gl, mContext);
            }
        }
    }
}
