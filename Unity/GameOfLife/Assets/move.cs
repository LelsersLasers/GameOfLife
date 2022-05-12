using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class move : MonoBehaviour
{
    public static float sensitivity = 100f;
    void Start() {
        Cursor.lockState = CursorLockMode.Locked;
    }

    void Update() {
        float mouseX = Input.GetAxis("Mouse X") * sensitivity * Time.deltaTime;
        float mouseY = Input.GetAxis("Mouse Y") * sensitivity * Time.deltaTime;

        transform.localRotation = Quaternion.Euler(Mathf.Clamp(-mouseY, -90f, 90f), 0f, 0f);
        //transform.localRotation = Quaternion.Euler(mouseX, 0f, 0f);
    }
}
